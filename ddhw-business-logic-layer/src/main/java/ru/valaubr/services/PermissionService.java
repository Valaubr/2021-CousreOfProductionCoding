package ru.valaubr.services;

import org.springframework.stereotype.Service;
import ru.valaubr.dao.impl.CatalogDaoImpl;
import ru.valaubr.dao.impl.DocumentDaoImpl;
import ru.valaubr.enums.Importance;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.models.ServiceUser;

@Service
public class PermissionService {

    private CatalogWhiteList catalogWhiteList;
    private DocumentDaoImpl documentDaoImpl;

    public void setCatalogPermToUser(ServiceUser serviceUser, CatalogDaoImpl catalogDaoImpl, Permissions permissions) {

    }

    public void changeDocImportance(DocumentDaoImpl documentDaoImpl, Importance importance) {

    }

    public void changeUserRole(ServiceUser serviceUser, Role role) {

    }
}
