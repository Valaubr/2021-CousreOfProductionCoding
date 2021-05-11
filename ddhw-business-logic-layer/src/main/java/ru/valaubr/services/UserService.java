package ru.valaubr.services;

import org.springframework.stereotype.Service;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;
import ru.valaubr.models.ServiceUser;

@Service
public class UserService {
    private ServiceUser serviceUser;

    public void createUser(String password, String fName, String lName, String email) {
    }

    public Permissions checkPerm() {
        return Permissions.READ;
    }
}
