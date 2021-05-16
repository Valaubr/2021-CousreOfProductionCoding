package ru.valaubr.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.valaubr.models.ModerationQueue;

import java.util.List;

public interface ModerationQueueRepo extends JpaRepository<ModerationQueue, Long> {
    @Query("from ModerationQueue mq, CatalogWhiteList cwl " +
            "where mq.catalog = cwl.catalog " +
            "and cwl.serviceUser.email = :email " +
            "and cwl.permissions = 'MODERATOR'")
    List<ModerationQueue> findModeratedCatalogsForUser(@Param("email") String email);
    @Query("from ModerationQueue mq where mq.catalog.id = :id")
    ModerationQueue findByCatalog(@Param("id") Long id);
}
