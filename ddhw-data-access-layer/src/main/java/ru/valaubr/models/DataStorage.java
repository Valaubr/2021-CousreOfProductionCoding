package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="Discriminator", discriminatorType=DiscriminatorType.STRING)
public abstract class DataStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private DataStorage parent;
    private String name;
    @Column(unique = true)
    private String pathOnDisk;
    @Temporal(TemporalType.DATE)
    private Date dateOfCreation;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private ServiceUser author;
}
