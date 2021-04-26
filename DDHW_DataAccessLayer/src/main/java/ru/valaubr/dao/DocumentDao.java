package ru.valaubr.services.dao;

import ru.valaubr.services.enums.Importance;
import ru.valaubr.services.models.User;

public interface DocumentDao {
    boolean createDoc(Long parentID, String name, User author, String linkOnDisk, String description, Importance importance);
    boolean updateDoc(Long id, String name, String linkOnDisk, String description, Importance importance);
}
