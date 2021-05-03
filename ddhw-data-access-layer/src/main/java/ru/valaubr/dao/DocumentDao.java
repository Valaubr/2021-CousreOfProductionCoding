package ru.valaubr.dao;

import ru.valaubr.models.Document;

public interface DocumentDao {
    Document findById(Long id);
    void create(Document document);
    Document update(Document document);
    void delete(Document document);
}
