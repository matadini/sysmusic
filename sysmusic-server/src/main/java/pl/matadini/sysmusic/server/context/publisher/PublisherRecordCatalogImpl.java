package pl.matadini.sysmusic.server.context.publisher;

import lombok.Builder;

import javax.persistence.EntityManager;
import java.util.Optional;

@Builder
class PublisherRecordCatalogImpl implements PublisherRecordCatalog {

    EntityManager entityManager;

    @Override
    public Optional<PublisherRecord> get(Long recordId) {
        return Optional.of(entityManager.find(PublisherRecord.class, recordId));
    }
}
