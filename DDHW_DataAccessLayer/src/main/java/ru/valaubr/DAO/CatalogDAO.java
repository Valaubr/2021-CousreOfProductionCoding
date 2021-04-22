package ru.valaubr.DAO;

import ru.valaubr.models.Document;
import ru.valaubr.models.User;

import java.util.List;

public interface CatalogDAO {
    public List<Document> getAll(Long parenID);
    public void createCatalog(Long parentID, String name, User author, String linkOnDisk);
    public void updateCatalog(Long id, String name, String linkOnDisk);
}
