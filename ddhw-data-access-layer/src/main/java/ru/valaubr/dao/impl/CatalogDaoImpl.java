package ru.valaubr.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.valaubr.dao.CatalogDao;
import ru.valaubr.models.DataStorage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Repository
public class CatalogDaoImpl implements CatalogDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<DataStorage> findAllByParent(Long id) {
        Query query = entityManager.createQuery("select ds from DataStorage ds where id = :id", DataStorage.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void create(DataStorage ds) {
        entityManager.persist(ds);
    }

    @Override
    @Transactional
    public DataStorage update(DataStorage ds) {
        return entityManager.merge(ds);
    }

    @Override
    @Transactional
    public void delete(DataStorage ds) {
        entityManager.remove(ds);
    }
}