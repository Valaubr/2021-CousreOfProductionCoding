package ru.valaubr;

import ru.valaubr.DAO.CatalogDAO;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.DAO.DocumentDAO;
import ru.valaubr.models.ModerationQueue;
import ru.valaubr.models.User;
import ru.valaubr.enums.Permissions;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j(topic = "CatalogService")
public class CatalogService {
    private ModerationQueue moderationQueue;
    private CatalogWhiteList catalogWhiteList;
    private CatalogDAO catalogDAO;

    public void createCatalog(Long parantID,User user, String name) {
        if (checkPerm(user) != Permissions.READ) {
            catalogDAO.createCatalog(parantID, name, user);
        }
    }

    public void addDocsToCatalog(User user, List<DocumentDAO> documentDAOS, CatalogDAO root) {
        if (checkPerm(user) == Permissions.READ) {
            sendToModeration(documentDAOS, root);
        }
    }

    public void changeCatalogConfig(Long id, String name, String linkOnDisk, User user) {
        if (checkPerm(user) == Permissions.MODERATOR) {
            catalogDAO.updateCatalog(id, name, linkOnDisk);
        }
    }

    private void sendToModeration(List<DocumentDAO> documentDAOS, CatalogDAO root) {
        // insert into table
        moderationQueue.setCatalogDAO(root);
        moderationQueue.setDocumentDAOS(documentDAOS);
    }

    private Permissions checkPerm(User user) {
        //"select permissions from catalogWhiteList where user = " + user;
        return catalogWhiteList.getPermissions();
    }
}
