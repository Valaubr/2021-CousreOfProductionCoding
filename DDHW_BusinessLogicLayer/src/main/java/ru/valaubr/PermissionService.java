import models.Catalog;
import models.CatalogWhiteList;
import models.Document;
import models.User;
import enums.Importance;
import enums.Permissions;
import enums.Role;

public class PermissionService {
    private CatalogWhiteList catalogWhiteList;
    private Document document;

    public void setCatalogPermToUser(User user, Catalog catalog, Permissions permissions) {

    }

    public void changeDocImportance(Document document, Importance importance) {

    }

    public void changeUserRole(User user, Role role) {

    }
}
