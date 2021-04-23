package ru.valaubr;

import ru.valaubr.DAO.CatalogDAO;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.DAO.DocumentDAO;
import ru.valaubr.models.User;
import ru.valaubr.enums.Importance;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;

public class PermissionService {
    private CatalogWhiteList catalogWhiteList;
    private DocumentDAO documentDAO;

    public void setCatalogPermToUser(User user, CatalogDAO catalogDAO, Permissions permissions) {

    }

    public void changeDocImportance(DocumentDAO documentDAO, Importance importance) {

    }

    public void changeUserRole(User user, Role role) {

    }
}
