package ru.valaubr.servicelayer;

import ru.valaubr.servicelayer.models.User;
import ru.valaubr.servicelayer.services.PermissionService;
import ru.valaubr.servicelayer.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class AdminController {
    private UserService userService;
    private PermissionService permissionService;

    public List<User> getAllUser() {
        return new ArrayList<>();
    }

    public void addUser() {

    }

    public void changeUserRole() {

    }

    public void changeDocImportance() {

    }

    public void setModerator() {

    }
}
