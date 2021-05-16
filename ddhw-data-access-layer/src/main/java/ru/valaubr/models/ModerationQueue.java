package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
public class ModerationQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DataStorage catalog;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Document> documents;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ServiceUser sender;
}
