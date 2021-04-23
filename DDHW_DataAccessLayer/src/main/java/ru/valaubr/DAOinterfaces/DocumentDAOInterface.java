package ru.valaubr.DAO;

import ru.valaubr.enums.Importance;
import ru.valaubr.models.User;

public interface DocumentDAO {
    public void createDoc(Long parentID, String name, User author, String linkOnDisk, String description, Importance importance);
    public void updateDoc(Long id, String name, String linkOnDisk, String description, Importance importance);
}
