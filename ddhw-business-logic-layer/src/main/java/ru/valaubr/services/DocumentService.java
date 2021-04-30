package ru.valaubr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valaubr.dao.DocumentDao;
import ru.valaubr.enums.Importance;
import ru.valaubr.models.Document;
import ru.valaubr.models.User;

@Service
public class DocumentService {
    @Autowired
    private DocumentDao documentDaoImpl;

    public Document getDoc(Long id) {
        return documentDaoImpl.getDoc(id);
    }

    public boolean updateDoc(Long id, String name, String linkOnDisk, String description, Importance importance) {
        return documentDaoImpl.updateDoc(id, name, linkOnDisk, description, importance);
    }

    public boolean createDoc(Long parentID, String name, User author, String linkOnDisk, String description, Importance importance) {
        return documentDaoImpl.createDoc(parentID, name, author, linkOnDisk, description, importance);
    }

    public void CopyDoc() {

    }

    private void SendMessage() {

    }

    private void checkAuthor() {

    }

    private void upgradeVersion() {

    }
}
