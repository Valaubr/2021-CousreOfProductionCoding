package ru.valaubr.servicelayer.services;

import ru.valaubr.servicelayer.models.User;
import ru.valaubr.servicelayer.enums.Permissions;
import ru.valaubr.servicelayer.enums.Role;

public class UserService {
    private User user;

    public User findByLogin(String login) {
        return null;
    }

    public String findByLogPass() {
        return "Token";
    }

    public void createUser(String password, String fName, String lName, String email) {
        user.setPassword(password);
        user.setFName(fName);
        user.setLName(lName);
        user.setEmail(email);
        user.setRole(Role.USER);
        //user.save();
    }

    public Permissions checkPerm() {
        return Permissions.READ;
    }
}
