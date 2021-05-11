package ru.valaubr.services;

import org.springframework.stereotype.Service;
import ru.valaubr.enums.Importance;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;
import ru.valaubr.jpa.CatalogRepo;
import ru.valaubr.jpa.DocumentRepo;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.models.ServiceUser;

@Service
public class PermissionService {

    private CatalogWhiteList catalogWhiteList;
    private DocumentRepo documentRepo;

    public void setCatalogPermToUser(ServiceUser serviceUser, CatalogRepo catalogRepo, Permissions permissions) {

    }

    public void changeDocImportance(DocumentRepo documentRepo, Importance importance) {

    }

    public void changeUserRole(ServiceUser serviceUser, Role role) {

    }
}
