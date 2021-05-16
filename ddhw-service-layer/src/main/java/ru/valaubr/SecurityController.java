package ru.valaubr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.valaubr.services.security.AuthData;
import ru.valaubr.services.security.JwtProvider;
import ru.valaubr.models.ServiceUser;
import ru.valaubr.services.UserService;

@RestController
public class SecurityController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider provider;

    @PostMapping("/login")
    public String login(@RequestBody AuthData authData) {
        ServiceUser serviceUser = userService.findByEmailAndPassword(authData.getLogin(), authData.getPassword());
        String token = provider.generateToken(serviceUser.getEmail());
        return token;
    }
}
