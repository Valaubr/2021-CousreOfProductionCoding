package ru.valaubr.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @OneToOne(cascade = CascadeType.ALL)
    private DataStorage catalog;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Document> documents;
    @OneToOne(cascade = CascadeType.ALL)
    private ServiceUser sender;
}
