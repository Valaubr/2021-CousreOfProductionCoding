package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.FileTypesWhiteList;
import ru.valaubr.enums.Importance;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Document extends DataStorage {
    @ManyToOne
    private DataStorage parent;
    private String description;
    @Enumerated(EnumType.STRING)
    private Importance importance;
    private Integer version;
    @ManyToOne
    private DataStorage oldVersion;
    private FileTypesWhiteList fileType;
}
