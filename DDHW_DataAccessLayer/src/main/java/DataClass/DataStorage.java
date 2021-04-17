package DataClass;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class DataStorage {
    private Long id;
    private String name;
    private LocalDateTime dateOfCreation;
    private User author;
}
