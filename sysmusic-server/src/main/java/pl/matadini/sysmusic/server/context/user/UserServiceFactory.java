package pl.matadini.sysmusic.server.context.user;

import org.modelmapper.ModelMapper;

import javax.persistence.EntityManagerFactory;

class UserServiceFactory {

    public static UserService create(EntityManagerFactory entityManagerFactory) throws UserServiceException {
        UserService userService = UserServiceImpl.builder()
                .userRepository(new UserRepositoryImpl(entityManagerFactory.createEntityManager()))
                .modelMapper(new ModelMapper())
                .build();
        userService.initialize();
        return userService;
    }

    private UserServiceFactory() {

    }
}
