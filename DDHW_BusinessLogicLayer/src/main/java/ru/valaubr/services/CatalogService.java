package ru.valaubr;

import lombok.extern.slf4j.Slf4j;
import ru.valaubr.dao.CatalogDao;
import ru.valaubr.dao.DocumentDao;
import ru.valaubr.dao.Impl.CatalogDaoImpl;
import ru.valaubr.enums.Permissions;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.models.Document;
import ru.valaubr.models.ModerationQueue;
import ru.valaubr.models.User;

import java.util.List;

@Slf4j(topic = "CatalogService")
public class CatalogService {
    private ModerationQueue moderationQueue;
    private CatalogWhiteList catalogWhiteList;
    private CatalogDaoImpl catalogDaoImpl;

    public List<Document> getCatalogData(Long id) {
        return catalogDaoImpl.getAll(id);
    }

    public void createCatalog(Long parantID, User user, String name) {
        if (checkPerm(user) != Permissions.READ) {
            catalogDaoImpl.createCatalog(parantID, name, user);
        }
    }

    public void addDocsToCatalog(User user, List<DocumentDao> documentDAOS, CatalogDao root) {
        if (checkPerm(user) == Permissions.READ) {
            sendToModeration(documentDAOS, root);
        }
    }

    public void changeCatalogConfig(Long id, String name, String linkOnDisk, User user) {
        if (checkPerm(user) == Permissions.MODERATOR) {
            catalogDaoImpl.updateCatalog(id, name, linkOnDisk);
        }
    }

    private void sendToModeration(List<DocumentDao> documentDao, CatalogDao root) {
//         insert into table
//        moderationQueue.setCatalogDaoImpl(root);
//        moderationQueue.setDocumentDaoImpl(documentDao);
    }

    private Permissions checkPerm(User user) {
        //"select permissions from catalogWhiteList where user = " + user;
        return catalogWhiteList.getPermissions();
    }
}
