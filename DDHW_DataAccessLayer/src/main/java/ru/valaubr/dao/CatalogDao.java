package ru.valaubr.services.dao;

import ru.valaubr.services.models.Document;
import ru.valaubr.services.models.User;

import java.util.List;

public interface CatalogDao {
    List<Document> getAll(Long parenID);
    void createCatalog(Long parentID, String name, User author);
    void updateCatalog(Long id, String name, String linkOnDisk);
}
