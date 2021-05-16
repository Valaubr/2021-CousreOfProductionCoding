package ru.valaubr.dto;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.Role;
import ru.valaubr.models.ServiceUser;

import java.util.Optional;

@Getter
@Setter
public class UserDto {
    private String email;
    private String fName;
    private String lName;
    private String role;

    public UserDto(Optional<ServiceUser> user) {
        email = user.get().getEmail();
        fName = user.get().getFName();
        lName = user.get().getLName();
        role = user.get().getRole().toString();
    }
}
