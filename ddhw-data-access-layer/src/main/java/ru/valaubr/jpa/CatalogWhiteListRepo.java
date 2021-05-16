package ru.valaubr.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.valaubr.models.CatalogWhiteList;

@Repository
public interface CatalogWhiteListRepo extends JpaRepository<CatalogWhiteList, Long> {
    @Query("from CatalogWhiteList cwl where cwl.serviceUser.id = :email and cwl.catalog.id = :id")
    CatalogWhiteList getPermForCatalog(@Param("email") Long userId, @Param("id") Long id);
}
