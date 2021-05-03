package ru.valaubr.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.valaubr.dao.DocumentDao;
import ru.valaubr.models.Document;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Slf4j
@Repository
public class DocumentDaoImpl implements DocumentDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Document findById(Long id) {
        return entityManager.find(Document.class, id);
    }

    @Override
    @Transactional
    public void create(Document document) {
        entityManager.persist(document);
    }

    @Override
    @Transactional
    public Document update(Document document) {
        return entityManager.merge(document);
    }

    @Override
    @Transactional
    public void delete(Document document) {
        entityManager.remove(document);
    }
}
