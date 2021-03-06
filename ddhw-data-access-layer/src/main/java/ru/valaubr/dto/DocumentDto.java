package ru.valaubr.dto;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.FileTypesWhiteList;
import ru.valaubr.enums.Importance;
import ru.valaubr.models.Document;

import java.util.Date;
import java.util.Optional;

@Getter
@Setter
public class DocumentDto {
    private Long id;
    private Long parentId;
    private String name;
    private String pathOnDisk;
    private Date dateOfCreation;
    private String author;
    private String description;
    private Importance importance;
    private Integer version;
    private Long oldVersion;
    private FileTypesWhiteList fileType;

    public DocumentDto(Optional<Document> document) {
        id = document.get().getId();
        parentId = document.get().getParent().getId();
        name = document.get().getName();
        pathOnDisk = document.get().getPathOnDisk();
        dateOfCreation = document.get().getDateOfCreation();
        author = document.get().getAuthor().getEmail();
        description = document.get().getDescription();
        importance = document.get().getImportance();
        version = document.get().getVersion();
        if (document.get().getOldVersion() != null) {
            oldVersion = document.get().getOldVersion().getId();
        }
        fileType = document.get().getFileType();
    }
}
