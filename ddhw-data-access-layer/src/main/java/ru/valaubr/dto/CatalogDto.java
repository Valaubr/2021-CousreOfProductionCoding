package ru.valaubr.dto;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.models.DataStorage;

import java.util.Date;

@Getter
@Setter
public class CatalogDto {
    private Long id;
    private String name;
    private Long parentId;
    private String pathOnDisk;
    private Date dateOfCreation;
    private String author;

    public CatalogDto(DataStorage dataStorage) {
        id = dataStorage.getId();
        name = dataStorage.getName();
        pathOnDisk = dataStorage.getPathOnDisk();
        dateOfCreation = dataStorage.getDateOfCreation();
        author = dataStorage.getAuthor().getEmail();
        parentId = dataStorage.getParentId();
    }
}
