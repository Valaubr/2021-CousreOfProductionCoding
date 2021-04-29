package ru.valaubr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.valaubr.services.UserService;

@Controller
public class SecurityController {
    @Autowired
    private UserService userService;

    public String login() {
        return null;
    }

    public String registration() {
        return null;
    }
}
