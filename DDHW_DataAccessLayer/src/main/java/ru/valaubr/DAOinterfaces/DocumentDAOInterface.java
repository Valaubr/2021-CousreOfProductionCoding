package ru.valaubr.DAOinterfaces;

import ru.valaubr.enums.Importance;
import ru.valaubr.models.User;

public interface DocumentDAOInterface {
    public boolean createDoc(Long parentID, String name, User author, String linkOnDisk, String description, Importance importance);
    public boolean updateDoc(Long id, String name, String linkOnDisk, String description, Importance importance);
}
