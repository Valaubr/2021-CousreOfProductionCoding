import DataClass.*;
import enums.Permissions;

import java.util.List;

public class CatalogService {
    private ModerationQueue moderationQueue;
    private CatalogWhiteList catalogWhiteList;
    private Catalog catalog;

    public void CreateCatalog(User user, String name, Catalog root) {
        if (checkPerm(user) != Permissions.READ) {
            create();
        }
    }

    public void AddDocsToCatalog(User user, List<Document> documents, Catalog root) {
        if (checkPerm(user) == Permissions.READ) {
            sendToModeration();
        } else {
            add();
        }
    }

    public void changeCatalogName(User user, Catalog catalog, String name) {
        if (checkPerm(user) == Permissions.MODERATOR) {
            changeName();
        }
    }

    private void changeName() {
    }

    private void create() {
    }

    private void add() {
    }

    private void sendToModeration() {

    }

    private Permissions checkPerm(User user) {
        //"select permissions from catalogWhiteList where user = " + user;
        return catalogWhiteList.getPermissions();
    }
}
