package ru.valaubr.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ru.valaubr.dto.AcceptorDto;
import ru.valaubr.dto.ModerationQueueDto;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;
import ru.valaubr.jpa.*;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.models.Document;
import ru.valaubr.models.ModerationQueue;
import ru.valaubr.models.ServiceUser;
import ru.valaubr.services.security.JwtProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
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
    @Autowired
    private CatalogRepo catalogRepo;

    public List<ModerationQueueDto> getAllDocToModeration(String auth) {
        auth = provider.getLoginFromToken(auth.substring(7));
        List<ModerationQueue> moderationQueues = repo.findModeratedCatalogsForUser(auth);
        List<ModerationQueueDto> out = new ArrayList<>();
        moderationQueues.forEach(moderationQueue -> out.add(new ModerationQueueDto(moderationQueue)));
        return out;
    }

    //Я думаю что перемещение не являетсяя модификацией документа по этому не создаю копию документа
    public ResponseEntity accept(String auth, AcceptorDto input) {
        auth = provider.getLoginFromToken(auth.substring(7));
        if (checkPerm(auth, input)) {
            ModerationQueue edited = repo.findById(input.getId()).get();
            Collection<Document> editor = repo.findById(input.getId()).get().getDocuments();
            Iterator<Document> i = editor.iterator();
            while (i.hasNext()) {
                Document d = i.next();
                if (input.getDocuments().contains(d.getId())) {
                    try {
                        FileCopyUtils.copy(new File(d.getPathOnDisk()), new File(edited.getCatalog().getPathOnDisk() + d.getName()));
                        d.setPathOnDisk(edited.getCatalog().getPathOnDisk() + d.getName());
                        documentRepo.save(d);
                        i.remove();
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
            edited.setDocuments(editor);
            repo.save(edited);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity reject(String auth, AcceptorDto input) {
        if (checkPerm(auth, input)) {
            ModerationQueue edited = repo.findById(input.getId()).get();
            Collection<Document> editor = repo.findById(input.getId()).get().getDocuments();
            Iterator<Document> i = editor.iterator();
            while (i.hasNext()) {
                Document d = i.next();
                if (input.getDocuments().contains(d.getId())) {
                    d.setPathOnDisk(edited.getCatalog().getPathOnDisk() + d.getName());
                    documentRepo.save(d);
                    i.remove();

                    edited.setDocuments(editor);
                    repo.save(edited);
                    return ResponseEntity.ok().build();
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    private boolean checkPerm(String auth, AcceptorDto input) {
        ServiceUser user = repoUser.findByEmail(auth);
        CatalogWhiteList check = repoCwl.getPermForCatalog(user.getId(), repo.findById(input.getId()).get().getCatalog().getId());
        if (check != null && check.getPermissions() == Permissions.MODERATOR || user.getRole() == Role.ROLE_ADMINISTRATOR) {
            return true;
        }
        return false;
    }
}
