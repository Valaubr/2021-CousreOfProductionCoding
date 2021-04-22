package ru.valaubr;

import ru.valaubr.models.Catalog;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.models.Document;
import ru.valaubr.models.ModerationQueue;
import ru.valaubr.models.User;
import ru.valaubr.enums.Permissions;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j(topic = "CatalogService")
public class CatalogService {
    private ModerationQueue moderationQueue;
    private CatalogWhiteList catalogWhiteList;
    private Catalog catalog;

    public void CreateCatalog(User user, String name, Catalog root) {
        if (checkPerm(user) != Permissions.READ) {
            File file = new File(root.getPathOnDisk() + name);
            file.mkdir();
        }
    }

    public void AddDocsToCatalog(User user, List<Document> documents, Catalog root) {
        if (checkPerm(user) == Permissions.READ) {
            sendToModeration(documents, root);
        } else {
            add(documents, root);
        }
    }

    public void changeCatalogName(User user, Catalog catalog, String name) {
        if (checkPerm(user) == Permissions.MODERATOR) {
            changeName(catalog, name);
        }
    }

    private void changeName(Catalog catalog, String name) {
        catalog.setName(name);
    }

    private void add(List<Document> documents, Catalog root) {
        documents.forEach(document -> {
            try {
                Files.move(Path.of(document.getPathOnDisk()), Path.of(root.getPathOnDisk()));
            } catch (IOException e) {
                log.error("Exception with move file:", e);
            }
        });
    }

    private void sendToModeration(List<Document> documents, Catalog root) {
        // insert into table
        moderationQueue.setCatalog(root);
        moderationQueue.setDocuments(documents);
    }

    private Permissions checkPerm(User user) {
        //"select permissions from catalogWhiteList where user = " + user;
        return catalogWhiteList.getPermissions();
    }
}
