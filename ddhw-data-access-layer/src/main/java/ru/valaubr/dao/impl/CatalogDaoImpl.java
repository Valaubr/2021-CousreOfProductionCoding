package ru.valaubr.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.valaubr.dao.CatalogDao;
import ru.valaubr.models.DataStorage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class CatalogDaoImpl implements CatalogDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<DataStorage> findAllByParent(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DataStorage> query = criteriaBuilder.createQuery(DataStorage.class);
        Root doc = query.from(DataStorage.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.ge(doc.get("id"), id));
        return entityManager.createQuery(query.where(predicates.toArray(new Predicate[0]))).getResultList();
    }

    @Override
    public void create(DataStorage ds) {
        entityManager.persist(ds);
    }

    @Override
    public DataStorage update(DataStorage ds) {
        return entityManager.merge(ds);
    }

    @Override
    public void delete(DataStorage ds) {
        entityManager.remove(ds);
    }
}