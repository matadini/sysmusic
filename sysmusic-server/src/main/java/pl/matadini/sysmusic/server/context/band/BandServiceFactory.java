package pl.matadini.sysmusic.server.context.band;

import org.modelmapper.ModelMapper;

import javax.persistence.EntityManagerFactory;

public class BandServiceFactory {

    public static BandService create(EntityManagerFactory entityManagerFactory) {
        return BandServiceImpl.builder()
                .modelMapper(new ModelMapper())
                .bandRepository(new BandRepositoryImpl(entityManagerFactory.createEntityManager()))
                .build();
    }

    private BandServiceFactory() {

    }
}
