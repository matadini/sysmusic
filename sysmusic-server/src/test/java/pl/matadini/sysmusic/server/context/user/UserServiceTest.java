package pl.matadini.sysmusic.server.context.user;

import org.junit.jupiter.api.Test;
import pl.matadini.sysmusic.server.common.test.H2Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends H2Test {

    @Test
    public void test() throws UserServiceException {

        /**
         * Create
         */
        UserService userService = UserServiceFactory.create(entityManagerFactory);
        Long userId = userService.addUser("kazia", "haslo");
        assertTrue(userService.authorize("kazia", "haslo"));

        /**
         * Read
         */
        List<UserDto> userDtos = userService.getUsers();
        assertEquals(2, userDtos.size());

        /**
         * Update
         */
        userService.changePassword(userId, "haslo-update");
        assertFalse(userService.authorize("kazia", "haslo"));
        assertTrue(userService.authorize("kazia", "haslo-update"));

        /**
         * Delete
         */
        userService.removeUser(userId);
        assertFalse(userService.authorize("kazia", "haslo-update"));


    }
}