package ru.valaubr.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.valaubr.dto.DocumentDto;
import ru.valaubr.enums.FileTypesWhiteList;
import ru.valaubr.enums.Importance;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;
import ru.valaubr.jpa.*;
import ru.valaubr.models.*;
import ru.valaubr.services.security.JwtProvider;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.util.*;

@Service
@Slf4j
public class DocumentService {
    @Autowired
    private ServiceUserRepo user;
    @Autowired
    private CatalogWhiteListRepo whiteListRepo;
    @Autowired
    private JwtProvider provider;
    @Autowired
    private DocumentRepo documentRepo;
    @Autowired
    private CatalogRepo catalogRepo;
    @Autowired
    private ModerationQueueRepo moderationQueueRepo;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public DocumentDto getDoc(Long id) {
        Optional<Document> a = documentRepo.findById(id);
        if (!a.isEmpty()) {
            new DocumentDto(a);
        }
        return null;
    }

    public ResponseEntity<Object> updateDoc(MultipartFile file, String auth, Long id, String description, String importance) {
        auth = provider.getLoginFromToken(auth.substring(7));
        ServiceUser sr = user.findByEmail(auth);
        if (id != null) {
            Document doc = documentRepo.findById(id).get();
            if (!checkPerm(sr, id) && !checkAuthor(doc, sr)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            Document updatedDoc = new Document(doc);
            updatedDoc.setDateOfCreation(doc.getDateOfCreation());
            updatedDoc.setName(file.getOriginalFilename());
            updatedDoc.setParent(doc.getParent());
            updatedDoc.setPathOnDisk(doc.getParent().getPathOnDisk() + file.getOriginalFilename());
            updatedDoc.setDescription(description);
            updatedDoc.setImportance(Importance.valueOf(importance));
            updatedDoc.setAuthor(sr);
            updatedDoc.setVersion(doc.getVersion() + 1);
            updatedDoc.setOldVersion(doc);
            updatedDoc.setIsActive(true);
            doc.setIsActive(false);
            boolean check = createFile(file, doc);
            if (!check) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            documentRepo.save(updatedDoc);
            documentRepo.save(doc);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<Object> createDoc(MultipartFile file, String auth, Long parentId, String description, String importance) {
        auth = provider.getLoginFromToken(auth.substring(7));
        ServiceUser sr = user.findByEmail(auth);
        Document doc = new Document();
        if (!checkPerm(sr, parentId)) {
            ModerationQueue mq = new ModerationQueue();
            mq.setCatalog(catalogRepo.findById(parentId).get());
            List<Document> docs = new ArrayList<>();
            Document a = insertDocData(doc, file, parentId, description, importance,sr, false);
            if (a != null) {
                docs.add(a);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            mq.setDocuments(docs);
            mq.setSender(sr);
            if (mq.getCatalog() != null && !docs.isEmpty()) {
                moderationQueueRepo.save(mq);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            documentRepo.save(doc);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        insertDocData(doc, file, parentId, description, importance, sr, true);
        documentRepo.save(doc);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private Document insertDocData(Document doc, MultipartFile file, Long parentId, String description, String importance, ServiceUser sr, boolean isActive) {
        doc.setDateOfCreation(Date.from(Clock.systemDefaultZone().instant()));
        doc.setFileType(FileTypesWhiteList.valueOf(file.getContentType().split("/")[1].toUpperCase()));
        doc.setName(file.getOriginalFilename());
        doc.setPathOnDisk(catalogRepo.findById(parentId).get().getPathOnDisk() + file.getOriginalFilename());
        doc.setParent(catalogRepo.findById(parentId).get());
        doc.setDescription(description);
        doc.setImportance(Importance.valueOf(importance.toUpperCase()));
        doc.setAuthor(sr);
        doc.setVersion(1);
        doc.setOldVersion(null);
        doc.setIsActive(isActive);
        boolean check = createFile(file, doc);
        if (check) {
            return doc;
        }
        return null;
    }

    private boolean createFile(MultipartFile file, Document doc) {
        try {
            if (!file.isEmpty()) {
                File newDoc = new File(doc.getPathOnDisk());
                if (!newDoc.createNewFile()){
                    return false;
                }
                file.transferTo(newDoc);
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
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

    public ResponseEntity updateDocWithMove(MultipartFile file, String auth, Long id, Long parentId, String description, String importance) {
        auth = provider.getLoginFromToken(auth.substring(7));
        ServiceUser sr = user.findByEmail(auth);
        if (id != null) {
            Document doc = documentRepo.findById(id).get();
            Document updatedDoc = new Document(doc);
            if (!checkPerm(sr, id) && !checkPerm(sr, parentId)) {
                ModerationQueue mq = new ModerationQueue();
                mq.setCatalog(catalogRepo.findById(parentId).get());
                List<Document> docs = new ArrayList<>();
                docs.add(insertDocData(updatedDoc, file, parentId, description, importance, sr, false));
                mq.setDocuments(docs);
                mq.setSender(sr);
                if (mq.getCatalog() != null && !docs.isEmpty()) {
                    moderationQueueRepo.save(mq);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                documentRepo.save(updatedDoc);
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            } else {
                doc.setIsActive(false);
                insertDocData(updatedDoc, file, parentId, description, importance, sr, true);
                documentRepo.save(doc);
                documentRepo.save(updatedDoc);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
