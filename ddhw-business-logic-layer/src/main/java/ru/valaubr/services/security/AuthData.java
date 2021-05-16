package ru.valaubr.services.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthData {
    private String login;
    private String password;
}
