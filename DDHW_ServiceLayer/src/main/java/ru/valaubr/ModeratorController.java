package ru.valaubr;

import ru.valaubr.DAO.CatalogDAO;
import ru.valaubr.DAO.DocumentDAO;
import ru.valaubr.models.ModerationQueue;

import java.util.List;
import java.util.Map;

public class ModeratorController {
    private UserService userService;
    private ModerationQueue moderationQueue;
    private Moderation moderation;

    public void checkPerm() {
        //userService.checkPerm(User);
    }

    public Map<CatalogDAO, List<DocumentDAO>> getQueue() {
        return null;
    }

    public void accept() {

    }

    public void reject() {

    }
}
