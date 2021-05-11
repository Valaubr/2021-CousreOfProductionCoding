package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class ServiceUser {
    private String fName;
    private String lName;
    private String password;
    @Id
    private String email; //Primary-key like login

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authority> role = new HashSet<>();
}
