package DataClass;

import enums.Permissions;
import lombok.Data;

@Data
public class CatalogWhiteList {
    private Long id;
    private User user;
    private Catalog catalog;
    private Permissions permissions;
}
