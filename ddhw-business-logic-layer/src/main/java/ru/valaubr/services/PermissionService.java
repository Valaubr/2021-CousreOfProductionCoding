package ru.valaubr.servicelayer.services;

import ru.valaubr.servicelayer.dao.Impl.CatalogDaoImpl;
import ru.valaubr.servicelayer.models.CatalogWhiteList;
import ru.valaubr.servicelayer.dao.Impl.DocumentDaoImpl;
import ru.valaubr.servicelayer.models.User;
import ru.valaubr.servicelayer.enums.Importance;
import ru.valaubr.servicelayer.enums.Permissions;
import ru.valaubr.servicelayer.enums.Role;

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
