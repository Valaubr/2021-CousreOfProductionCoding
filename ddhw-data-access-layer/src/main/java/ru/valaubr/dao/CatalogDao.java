package ru.valaubr.dao;

import ru.valaubr.models.DataStorage;

import java.util.List;


public interface CatalogDao {
    List<DataStorage> findAllByParent(Long id);
    void create(DataStorage ds);
    DataStorage update(DataStorage ds);
    void delete(DataStorage ds);
}
