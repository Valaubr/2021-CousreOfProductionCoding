package ru.valaubr.services;

import org.springframework.stereotype.Service;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;
import ru.valaubr.models.ServiceUser;

@Service
public class UserService {
    private ServiceUser serviceUser;

    public ServiceUser findByLogin(String login) {
        return null;
    }

    public String findByLogPass() {
        return "Token";
    }

    public void createUser(String password, String fName, String lName, String email) {
        serviceUser.setPassword(password);
        serviceUser.setFName(fName);
        serviceUser.setLName(lName);
        serviceUser.setEmail(email);
        serviceUser.setRole(Role.USER);
        //serviceUser.save();
    }

    public Permissions checkPerm() {
        return Permissions.READ;
    }
}
