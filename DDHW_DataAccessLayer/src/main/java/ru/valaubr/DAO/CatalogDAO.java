package ru.valaubr.DAO;

import ru.valaubr.models.Document;

import java.util.List;

public interface AbstractDAO {
    public List<Document> getAll();
}
