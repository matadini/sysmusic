package pl.matadini.sysmusic.server.context.publisher;

import java.util.Optional;

interface PublisherRecordCatalog {

    Optional<PublisherRecord> get(Long recordId);
}
