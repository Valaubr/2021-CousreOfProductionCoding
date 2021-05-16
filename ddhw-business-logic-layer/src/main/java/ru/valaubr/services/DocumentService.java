package ru.valaubr.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.valaubr.dto.DocumentDto;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;
import ru.valaubr.jpa.CatalogWhiteListRepo;
import ru.valaubr.jpa.DocumentRepo;
import ru.valaubr.jpa.ServiceUserRepo;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.models.Document;
import ru.valaubr.models.ServiceUser;
import ru.valaubr.services.security.JwtProvider;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    private ServiceUserRepo user;
    @Autowired
    private CatalogWhiteListRepo whiteListRepo;
    @Autowired
    private JwtProvider provider;
    @Autowired
    private DocumentRepo documentRepo;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public DocumentDto getDoc(Long id) {
        Optional<Document> a = documentRepo.findById(id);
        if (!a.isEmpty()) {
            new DocumentDto(a);
        }
        return null;
    }

    public ResponseEntity<Object> updateDoc(DocumentDto documentDto, String auth) {
        auth = provider.getLoginFromToken(auth.substring(7));
        ServiceUser sr = user.findByEmail(auth);
        Document doc = documentRepo.findById(documentDto.getId()).get();
        if (documentDto.getId() != null) {
            if (!checkPerm(sr, documentDto.getId()) || checkAuthor(doc, sr)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            //TODO придумать как не накидать тучу null в бд
            Document updatedDoc = new Document(doc);
            updatedDoc.setDateOfCreation(documentDto.getDateOfCreation());
            updatedDoc.setName(documentDto.getName());
            updatedDoc.setDateOfCreation(documentDto.getDateOfCreation());
            updatedDoc.setParentId(documentDto.getParentId());
            updatedDoc.setPathOnDisk(documentDto.getPathOnDisk());
            updatedDoc.setAuthor(sr);
            updatedDoc.setFolder(false);
            updatedDoc.setVersion(doc.getVersion() + 1);
            updatedDoc.setOldVersion(doc);
            updatedDoc.setIsActive(true);
            doc.setIsActive(false);
            documentRepo.save(updatedDoc);
            documentRepo.save(doc);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<Object> createDoc(DocumentDto documentDto, String auth) {
        auth = provider.getLoginFromToken(auth.substring(7));
        ServiceUser sr = user.findByEmail(auth);
        if (documentDto.getId() == null) {
            if (!checkPerm(sr, documentDto.getParentId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Document doc = new Document();
            doc.setDateOfCreation(Date.from(Instant.now()));
            doc.setName(documentDto.getName());
            doc.setDateOfCreation(documentDto.getDateOfCreation());
            doc.setParentId(documentDto.getParentId());
            doc.setPathOnDisk(documentDto.getPathOnDisk());
            doc.setDescription(documentDto.getDescription());
            doc.setFileType(documentDto.getFileType());
            doc.setImportance(documentDto.getImportance());
            doc.setAuthor(sr);
            doc.setFolder(false);
            doc.setVersion(1);
            doc.setOldVersion(null);
            doc.setIsActive(true);
            documentRepo.save(doc);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    private Boolean checkAuthor(Document doc, ServiceUser sr) {
        return doc.getAuthor().getEmail().equals(sr.getEmail());
    }

    private Boolean checkPerm(ServiceUser sr, Long usedId) {
        CatalogWhiteList a = whiteListRepo.getPermForCatalog(sr.getId(), usedId);
        if (sr.getRole() == Role.ROLE_ADMINISTRATOR) {
            return true;
        } else {
            return a != null && a.getPermissions() != Permissions.READ;
        }
    }
}
