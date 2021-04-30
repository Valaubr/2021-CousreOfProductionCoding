package ru.valaubr.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valaubr.dao.CatalogDao;
import ru.valaubr.dao.DocumentDao;
import ru.valaubr.enums.Permissions;
import ru.valaubr.models.User;

import java.util.List;

@Slf4j
@Service
public class CatalogService {
    @Autowired
    private CatalogDao catalogDaoImpl;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String getCatalogData(Long id) {
        return gson.toJson(catalogDaoImpl.getAll(id));
    }

    public void createCatalog(Long parantID, User user, String name) {
        //if (checkPerm(user) != Permissions.READ) {
            catalogDaoImpl.createCatalog(parantID, name, user);
        //}
    }

    public void updateCatalog(Long id, String name, String linkOnDisk) {
        //if (checkPerm(user) != Permissions.READ) {
        catalogDaoImpl.updateCatalog(id, name, linkOnDisk);
        //}
    }

    public void addDocsToCatalog(User user, List<DocumentDao> documentDAOS, CatalogDao root) {
        if (checkPerm(user) == Permissions.READ) {
            sendToModeration(documentDAOS, root);
        }
    }

    public void changeCatalogConfig(Long id, String name, String linkOnDisk, User user) {
        //if (checkPerm(user) == Permissions.MODERATOR) {
            catalogDaoImpl.updateCatalog(id, name, linkOnDisk);
        //}
    }

    private void sendToModeration(List<DocumentDao> documentDao, CatalogDao root) {
//         insert into table
//        moderationQueue.setCatalogDaoImpl(root);
//        moderationQueue.setDocumentDaoImpl(documentDao);
    }

    private Permissions checkPerm(User user) {
        //"select permissions from catalogWhiteList where user = " + user;
        //return catalogWhiteList.getPermissions();
        return null;
    }
}
