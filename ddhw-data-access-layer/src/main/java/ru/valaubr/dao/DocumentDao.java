package ru.valaubr.dao;

import ru.valaubr.enums.Importance;
import ru.valaubr.models.Document;
import ru.valaubr.models.User;

public interface DocumentDao {
    Document getDoc(Long id);

    boolean createDoc(Long parentID, String name, User author, String linkOnDisk, String description, Importance importance);

    boolean updateDoc(Long id, String name, String linkOnDisk, String description, Importance importance);
}
