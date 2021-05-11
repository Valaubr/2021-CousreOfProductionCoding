package ru.valaubr.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.valaubr.models.DataStorage;

import java.util.List;

public interface CatalogRepo extends JpaRepository<DataStorage, Long> {
    @Query("from DataStorage ds where ds.parentId = :id")
    List<DataStorage> findChildlessByParentId(@Param("id") Long id);
}
