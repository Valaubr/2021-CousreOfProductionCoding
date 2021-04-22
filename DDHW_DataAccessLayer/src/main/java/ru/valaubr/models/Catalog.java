package models;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Catalog extends DataStorage {
    private List<DataStorage> files;
    private String linkOnDisk;
}
