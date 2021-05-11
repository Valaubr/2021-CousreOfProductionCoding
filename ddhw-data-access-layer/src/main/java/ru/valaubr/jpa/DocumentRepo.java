package ru.valaubr.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.valaubr.models.Document;

public interface DocumentRepo extends JpaRepository<Document, Long> {
}
