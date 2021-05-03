package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.Permissions;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
public class CatalogWhiteList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private ServiceUser serviceUser;
    @OneToOne
    private DataStorage catalog;
    @Enumerated(EnumType.STRING)
    private Permissions permissions;
}
