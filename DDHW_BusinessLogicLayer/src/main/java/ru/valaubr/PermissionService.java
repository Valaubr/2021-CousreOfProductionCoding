package ru.valaubr;

import ru.valaubr.models.Catalog;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.models.Document;
import ru.valaubr.models.User;
import ru.valaubr.enums.Importance;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;

public class PermissionService {
    private CatalogWhiteList catalogWhiteList;
    private Document document;

    public void setCatalogPermToUser(User user, Catalog catalog, Permissions permissions) {

    }

    public void changeDocImportance(Document document, Importance importance) {

    }

    public void changeUserRole(User user, Role role) {

    }
}
