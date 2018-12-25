package pl.matadini.sysmusic.server.context.user;


import java.util.List;

interface UserService {

    Long addUser(String login, String password) throws UserServiceException;

    void removeUser(Long userId) throws UserServiceException;

    List<UserDto> getUsers() throws UserServiceException;

    void changePassword(Long userId, String newPassword) throws UserServiceException;

    boolean authorize(String login, String password) throws UserServiceException;

    void initialize() throws UserServiceException;
}

