package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public abstract class DataStorage {
    private Long id;
    private Long parentId;
    private String name;
    private String pathOnDisk;
    private Date dateOfCreation;
    private User author;
}
