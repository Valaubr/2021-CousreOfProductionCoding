package ru.valaubr.dto;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.models.ModerationQueue;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class ModerationQueueDto {
    private Long id;
    private Long catalog;
    private Collection<DocumentDto> documents;
    private String sender;

    public ModerationQueueDto() {
    }

    public ModerationQueueDto(ModerationQueue moderationQueue) {
        id = moderationQueue.getId();
        catalog = moderationQueue.getCatalog().getId();
        documents = new ArrayList<>();
        moderationQueue.getDocuments().forEach(document -> documents.add(new DocumentDto(java.util.Optional.ofNullable(document))));
        sender = moderationQueue.getSender().getEmail();
    }
}
