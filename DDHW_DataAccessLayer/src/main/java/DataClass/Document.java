package DataClass;

import enums.Importance;
import lombok.Data;

import java.util.List;
import java.io.File;

@Data
public class Document extends DataStorage {
    private List<File> files;
    private String description;
    private String LinkOnDisk;
    private Importance importance;
    private Integer version;
}
