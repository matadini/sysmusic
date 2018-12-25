package pl.matadini.sysmusic.server.context.user;

import lombok.AllArgsConstructor;
import org.pmw.tinylog.Logger;
import pl.matadini.sysmusic.server.common.jpa.JpaRepositoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
class UserRepositoryImpl implements UserRepository {

    EntityManager entityManager;

    @Override
    public User save(User entity) {
        return JpaRepositoryUtil.merge(entity, entityManager);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(JpaRepositoryUtil.read(User.class, id, entityManager));
    }

    @Override
    public void delete(User entity) {
        JpaRepositoryUtil.delete(entity, entityManager);
    }

    @Override
    public List<User> findAll() {
        String sql = "select u from User u order by u.userId desc";
        TypedQuery<User> query = entityManager.createQuery(sql, User.class);
        return query.getResultList();
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Long count() {
        String sql = "select count (u.userId) from User u";
        TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
        return query.getSingleResult();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        Optional<User> resoult = Optional.empty();
        try {

            String sql = "select u from User u where u.login = :login and u.password = :password";
            TypedQuery<User> query = entityManager.createQuery(sql, User.class);
            query.setParameter("login", login);
            query.setParameter("password", password);

            User singleResult = query.getSingleResult();
            resoult = Optional.ofNullable(singleResult);

        } catch (NoResultException ex) {

        } catch (Exception ex) {
            Logger.error(ex);
        }
        return resoult;
    }

    @Override
    public boolean existByLogin(String login) {
        return findByLogin(login).isPresent();
    }

    @Override
    public Optional<User> findByLogin(String login) {

        try {

            String sql = "select u from User u where u.login = :login";
            TypedQuery<User> query = entityManager.createQuery(sql, User.class);
            query.setParameter("login", login);
            User singleResult = query.getSingleResult();
            return Optional.ofNullable(singleResult);

        } catch (NoResultException ex) {

        } catch (Exception ex) {
            Logger.error(ex);
        }

        return Optional.empty();
    }
}
