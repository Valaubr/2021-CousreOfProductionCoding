package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.Importance;

@Getter
@Setter
public class Document extends DataStorage {
    //private List<File> files;
    private String description;
    private Importance importance;
    private Integer version;
    private Long oldVersion;
}