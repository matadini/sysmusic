package pl.matadini.sysmusic.server.context.user;

import pl.matadini.sysmusic.server.common.jpa.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginAndPassword(String login, String password);

    boolean existByLogin(String login);

    Optional<User> findByLogin(String login);
}
