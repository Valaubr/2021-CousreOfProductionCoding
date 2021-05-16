package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.Permissions;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CatalogWhiteList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private ServiceUser serviceUser;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DataStorage catalog;
    @Enumerated(EnumType.STRING)
    private Permissions permissions;
}
