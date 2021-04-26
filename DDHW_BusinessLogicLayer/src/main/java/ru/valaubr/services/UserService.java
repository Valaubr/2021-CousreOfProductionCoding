package ru.valaubr;

import ru.valaubr.models.User;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;

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
