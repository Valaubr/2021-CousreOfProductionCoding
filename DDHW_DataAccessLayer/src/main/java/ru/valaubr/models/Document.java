package models;

import enums.Importance;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.io.File;

@Getter
@Setter
public class Document extends DataStorage {
    private List<File> files;
    private String description;
    private String LinkOnDisk;
    private Importance importance;
    private Integer version;
}
