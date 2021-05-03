package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.Importance;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Document extends DataStorage {
    private String description;
    @Enumerated(EnumType.STRING)
    private Importance importance;
    private Integer version;
    @Transient
    private DataStorage oldVersion;
}
