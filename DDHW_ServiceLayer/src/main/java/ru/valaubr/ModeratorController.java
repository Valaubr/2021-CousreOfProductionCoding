package ru.valaubr;

import ru.valaubr.models.Catalog;
import ru.valaubr.models.Document;
import ru.valaubr.models.ModerationQueue;
import ru.valaubr.Moderation;
import ru.valaubr.UserService;

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
