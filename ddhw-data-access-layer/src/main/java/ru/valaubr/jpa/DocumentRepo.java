package ru.valaubr.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.valaubr.models.Document;

import java.util.Optional;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {
    @Override
    @Query("from Document d where d.id = :id")
    Optional<Document> findById(@Param("id") Long id);
}
