import DataClass.User;
import enums.Permissions;

public class UserService {
    private User user;

    public User findByLogin(String login) {
        return null;
    }

    public String findByLogPass() {
        return "Token";
    }

    public void createUser() {

    }

    public Permissions checkPerm() {
        return Permissions.READ;
    }
}
