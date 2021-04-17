package DataClass;

import enums.Role;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String fName;
    private String lName;
    private String login;
    private String password;
    private String Email;
    private Role role;
}
