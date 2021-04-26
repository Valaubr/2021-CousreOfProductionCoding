package ru.valaubr.servicelayer.dao;

import ru.valaubr.servicelayer.enums.Importance;
import ru.valaubr.servicelayer.models.User;

public interface DocumentDao {
    boolean createDoc(Long parentID, String name, User author, String linkOnDisk, String description, Importance importance);
    boolean updateDoc(Long id, String name, String linkOnDisk, String description, Importance importance);
}
