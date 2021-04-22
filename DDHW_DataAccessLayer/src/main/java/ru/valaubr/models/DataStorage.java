package models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class DataStorage {
    private Long id;
    private String name;
    private String pathOnDisk;
    private LocalDateTime dateOfCreation;
    private User author;
}
