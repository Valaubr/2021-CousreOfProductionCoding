package ru.valaubr.servicelayer.dao;

import ru.valaubr.servicelayer.models.Document;
import ru.valaubr.servicelayer.models.User;

import java.util.List;

public interface CatalogDao {
    List<Document> getAll(Long parenID);
    void createCatalog(Long parentID, String name, User author);
    void updateCatalog(Long id, String name, String linkOnDisk);
}
