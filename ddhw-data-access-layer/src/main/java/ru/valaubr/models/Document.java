package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.FileTypesWhiteList;
import ru.valaubr.enums.Importance;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@DiscriminatorValue("Document")
public class Document extends DataStorage {
    private String description;
    @Enumerated(EnumType.STRING)
    private Importance importance;
    private Integer version;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DataStorage oldVersion;
    private FileTypesWhiteList fileType;
    private Boolean isActive;


    public Document() {
        super();
    }

    public Document(Document doc) {
        this.setParent(doc.getParent());
        this.setName(doc.getName());
        this.setPathOnDisk(doc.getPathOnDisk());
        this.setDateOfCreation(doc.getDateOfCreation());
        this.setAuthor(doc.getAuthor());
        description = doc.getDescription();
        importance = doc.getImportance();
        version = doc.getVersion();
        oldVersion = doc.getOldVersion();
        fileType = doc.getFileType();
    }
}
