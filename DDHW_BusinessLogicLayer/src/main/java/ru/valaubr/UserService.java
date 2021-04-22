import models.User;
import enums.Permissions;
import enums.Role;

public class UserService {
    private User user;

    public User findByLogin(String login) {
        return null;
    }

    public String findByLogPass() {
        return "Token";
    }

    public void createUser(String login, String password, String fName, String lName, String email) {
        user.setLogin(login);
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
