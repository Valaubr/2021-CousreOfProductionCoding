package ru.valaubr.dto;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.models.Authority;
import ru.valaubr.models.ServiceUser;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    private String fName;
    private String lName;
    private Set<Authority> role = new HashSet<>();

    public UserDto(Optional<ServiceUser> user) {
        email = user.get().getEmail();
        fName = user.get().getFName();
        lName = user.get().getLName();
        role = user.get().getRole();
    }
}
