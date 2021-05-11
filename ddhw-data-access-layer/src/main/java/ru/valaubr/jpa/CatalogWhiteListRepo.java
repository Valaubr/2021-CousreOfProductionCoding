package ru.valaubr.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.valaubr.models.CatalogWhiteList;

public interface CatalogWhiteListRepo extends JpaRepository<CatalogWhiteList, Long> {
    @Query("from CatalogWhiteList cwl where cwl.serviceUser = :user and cwl.catalog = :catalog")
    CatalogWhiteList getRoleForCatalog(@Param("user") String email, @Param("catalog") Long id);
}
