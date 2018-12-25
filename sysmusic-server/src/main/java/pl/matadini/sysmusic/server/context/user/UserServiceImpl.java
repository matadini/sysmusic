package pl.matadini.sysmusic.server.context.user;

import com.google.common.collect.Lists;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import pl.matadini.sysmusic.server.common.util.ListUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Builder
class UserServiceImpl implements UserService {

    ModelMapper modelMapper;
    UserRepository userRepository;

    @Override
    public Long addUser(String login, String password) throws UserServiceException {
        try {
            User user = new User();
            user.login = login;
            user.password = password;
            user = userRepository.save(user);
            return user.userId;
        } catch (Exception ex) {
            throw new UserServiceException(ex);
        }
    }

    @Override
    public void removeUser(Long userId) throws UserServiceException {
        try {

            Optional<User> byId1 = userRepository.findById(userId);
            if (!byId1.isPresent()) {
                throw new UserException("brak w bazie uzytkownika o id: " + userId);
            }

            User byId = byId1.get();
            userRepository.delete(byId);

        } catch (Exception ex) {
            throw new UserServiceException(ex);
        }
    }


    @Override
    public List<UserDto> getUsers() throws UserServiceException {
        try {
            List<User> all = userRepository.findAll();
            return ListUtil.isNullOrEmpty(all) ? Lists.newArrayList()
                    : all.stream()
                    .map(item -> modelMapper.map(item, UserDto.class))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new UserServiceException(ex);
        }
    }

    @Override
    public void changePassword(Long userId, String newPassword) throws UserServiceException {
        try {

            Optional<User> byId = userRepository.findById(userId);
            if (!byId.isPresent()) {
                throw new UserException("brak w bazie uzytkownika o id: " + userId);
            }

            User user = byId.get();
            user.changePassword(newPassword);
            userRepository.save(user);

        } catch (UserException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public boolean authorize(String login, String password) throws UserServiceException {
        try {
            Optional<User> byLoginAndPassword = userRepository.findByLoginAndPassword(login, password);
            return byLoginAndPassword.isPresent();
        } catch (Exception ex) {
            throw new UserServiceException(ex);
        }
    }

    @Override
    public void initialize() throws UserServiceException {

        try {
            boolean existByLogin = userRepository.existByLogin(UserConst.ROOT_USER);
            if (!existByLogin) {
                this.addUser(UserConst.ROOT_USER, "");
            }
        } catch (Exception ex) {
            throw new UserServiceException(ex);
        }

    }
}
