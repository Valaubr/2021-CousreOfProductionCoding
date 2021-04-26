package ru.valaubr.servicelayer.services;

import lombok.extern.slf4j.Slf4j;
import ru.valaubr.servicelayer.dao.CatalogDao;
import ru.valaubr.servicelayer.dao.DocumentDao;
import ru.valaubr.servicelayer.dao.Impl.CatalogDaoImpl;
import ru.valaubr.servicelayer.enums.Permissions;
import ru.valaubr.servicelayer.models.CatalogWhiteList;
import ru.valaubr.servicelayer.models.Document;
import ru.valaubr.servicelayer.models.ModerationQueue;
import ru.valaubr.servicelayer.models.User;

import java.util.List;

@Slf4j(topic = "CatalogService")
public class CatalogService {
    private ModerationQueue moderationQueue;
    private CatalogWhiteList catalogWhiteList;
    private CatalogDaoImpl catalogDaoImpl = new CatalogDaoImpl();

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
