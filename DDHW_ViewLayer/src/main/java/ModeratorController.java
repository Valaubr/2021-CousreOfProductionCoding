import DataClass.Catalog;
import DataClass.Document;
import DataClass.ModerationQueue;

import java.util.List;
import java.util.Map;

public class ModeratorController {
    private UserService userService;
    private ModerationQueue moderationQueue;
    private Moderation moderation;

    public void checkPerm() {
        //userService.checkPerm(User);
    }

    public Map<Catalog, List<Document>> getQueue() {
        return null;
    }

    public void accept() {

    }

    public void reject() {

    }
}
