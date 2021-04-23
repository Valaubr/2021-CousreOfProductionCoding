package ru.valaubr;

import ru.valaubr.DAO.DocumentDAO;
import ru.valaubr.models.Document;

public class DocumentService {
    private DocumentDAO documentDAO;

    public Document get(Long id) {
        return documentDAO.getDoc(id);
    }

    public void update() {

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
