package ru.valaubr.services.models;

import ru.valaubr.services.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String fName;
    private String lName;
    private String password;
    private String Email; //Primary-key like login
    private Role role;
}
