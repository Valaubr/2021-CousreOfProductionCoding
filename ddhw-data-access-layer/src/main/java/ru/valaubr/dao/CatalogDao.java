package ru.valaubr.dao;

import ru.valaubr.models.Document;
import ru.valaubr.models.User;

import java.util.List;

public interface CatalogDao {
    List<Document> getAll(Long parenID);

    boolean createCatalog(Long parentID, String name, User author);

    boolean updateCatalog(Long id, String name, String linkOnDisk);
}
