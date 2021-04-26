package ru.valaubr;

import ru.valaubr.dao.Impl.CatalogDaoImpl;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.dao.Impl.DocumentDaoImpl;
import ru.valaubr.models.User;
import ru.valaubr.enums.Importance;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;

public class PermissionService {
    private CatalogWhiteList catalogWhiteList;
    private DocumentDaoImpl documentDaoImpl;

    public void setCatalogPermToUser(User user, CatalogDaoImpl catalogDaoImpl, Permissions permissions) {

    }

    public void changeDocImportance(DocumentDaoImpl documentDaoImpl, Importance importance) {

    }

    public void changeUserRole(User user, Role role) {

    }
}
