package ru.valaubr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.valaubr.models.ServiceUser;
import ru.valaubr.services.PermissionService;
import ru.valaubr.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    public List<ServiceUser> getAllUser() {
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
