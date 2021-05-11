package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class DataStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long parentId;
    private String name;
    private String pathOnDisk;
    @Temporal(TemporalType.DATE)
    private Date dateOfCreation;
    @ManyToOne(optional = false)
    private ServiceUser author;
    private boolean isFolder;
}
