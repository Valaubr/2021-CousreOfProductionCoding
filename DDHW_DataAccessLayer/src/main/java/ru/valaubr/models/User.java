package ru.valaubr.models;

import ru.valaubr.enums.Role;
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
