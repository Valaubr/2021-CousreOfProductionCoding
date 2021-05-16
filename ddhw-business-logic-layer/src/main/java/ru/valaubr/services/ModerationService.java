package ru.valaubr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.valaubr.dto.ModerationQueueDto;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;
import ru.valaubr.jpa.CatalogWhiteListRepo;
import ru.valaubr.jpa.DocumentRepo;
import ru.valaubr.jpa.ModerationQueueRepo;
import ru.valaubr.jpa.ServiceUserRepo;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.models.Document;
import ru.valaubr.models.ModerationQueue;
import ru.valaubr.models.ServiceUser;
import ru.valaubr.services.security.JwtProvider;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModerationService {
    @Autowired
    private ModerationQueueRepo repo;
    @Autowired
    private CatalogWhiteListRepo repoCwl;
    @Autowired
    private ServiceUserRepo repoUser;
    @Autowired
    private JwtProvider provider;
    @Autowired
    private DocumentRepo documentRepo;

    public List<ModerationQueueDto> getAllDocToModeration(String auth) {
        auth = provider.getLoginFromToken(auth.substring(7));
        List<ModerationQueue> moderationQueues = repo.findModeratedCatalogsForUser(auth);
        List<ModerationQueueDto> out = new ArrayList<>();
        moderationQueues.forEach(moderationQueue -> out.add(new ModerationQueueDto(moderationQueue)));
        return out;
    }

    //Я думаю что перемещение не являетсяя модификацией документа по этому не создаю копию документа
    public ResponseEntity accept(String auth, ModerationQueueDto input) {
        auth = provider.getLoginFromToken(auth.substring(7));
        if (checkPerm(auth, input)) {
            List<Document> documentList = new ArrayList<>();
            input.getDocuments().forEach(documentDto -> documentList.add(documentRepo.findById(documentDto.getId()).get()));
            documentList.forEach(document -> {
                document.setParentId(input.getCatalog());
                documentRepo.save(document);
            });
            repo.delete(repo.findById(input.getId()).get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity reject(String auth, ModerationQueueDto input) {
        if (checkPerm(auth, input)) {
            repo.delete(repo.findById(input.getId()).get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    private boolean checkPerm(String auth, ModerationQueueDto input) {
        ServiceUser user = repoUser.findByEmail(auth);
        CatalogWhiteList check = repoCwl.getPermForCatalog(user.getId(), input.getCatalog());
        if (check != null && check.getPermissions() == Permissions.MODERATOR || user.getRole() == Role.ROLE_ADMINISTRATOR) {
            return true;
        }
        return false;
    }
}
