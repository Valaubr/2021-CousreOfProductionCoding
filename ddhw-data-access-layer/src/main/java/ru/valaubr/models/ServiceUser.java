package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.Role;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ServiceUser {
    private String fName;
    private String lName;
    private String password;
    @Id
    private String Email; //Primary-key like login
    @Enumerated(EnumType.STRING)
    private Role role;
}
