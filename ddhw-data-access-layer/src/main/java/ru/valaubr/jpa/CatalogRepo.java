package ru.valaubr.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.valaubr.models.Catalog;
import ru.valaubr.models.DataStorage;

import java.util.List;

@Repository
public interface CatalogRepo extends JpaRepository<Catalog, Long> {
    @Query("from DataStorage c where c.parent.id = :id")
    List<DataStorage> findChildlessByParentId(@Param("id") Long id);
}
