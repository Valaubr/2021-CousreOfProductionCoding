package ru.valaubr.services;

import org.springframework.stereotype.Service;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;
import ru.valaubr.models.User;

@Service
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
