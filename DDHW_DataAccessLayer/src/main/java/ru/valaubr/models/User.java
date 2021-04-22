package models;

import enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String fName;
    private String lName;
    private String login;
    private String password;
    private String Email;
    private Role role;
}
