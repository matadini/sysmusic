package pl.matadini.sysmusic.server.context.publisher;

import org.modelmapper.ModelMapper;

import javax.persistence.EntityManagerFactory;

public class PublisherServiceFactory {

    public static PublisherService create(EntityManagerFactory entityManagerFactory) {
        return PublisherServiceImpl.builder()
                .modelMapper(new ModelMapper())
                .publisherRepository(new PublisherRepositoryImpl(entityManagerFactory.createEntityManager()))
                .publisherRecordCatalog(new PublisherRecordCatalogImpl(entityManagerFactory.createEntityManager()))
                .build();
    }

    private PublisherServiceFactory() {

    }
}
