package ru.valaubr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.valaubr.services.Moderation;
import ru.valaubr.services.UserService;

@Controller
public class ModeratorController {
    @Autowired
    private UserService userService;
    @Autowired
    private Moderation moderation;
}
