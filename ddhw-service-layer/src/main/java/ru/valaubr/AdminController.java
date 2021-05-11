package ru.valaubr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.valaubr.dto.UserDto;
import ru.valaubr.models.ServiceUser;
import ru.valaubr.services.PermissionService;
import ru.valaubr.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    public List<UserDto> getAllUser() {
        return new ArrayList<>();
    }

    @GetMapping("/api/admin/users")
    public List<UserDto> getAllUsers() {
        return null;
    }

    @PostMapping("/api/admin/users")
    public void addUser() {

    }

    @PutMapping("/api/admin/users")
    public void changeUserRole() {

    }

    @PutMapping("/api/admin/doc")
    public void changeDocImportance() {

    }

    @PostMapping("/api/admin/mod")
    public void setModerator() {

    }
}
