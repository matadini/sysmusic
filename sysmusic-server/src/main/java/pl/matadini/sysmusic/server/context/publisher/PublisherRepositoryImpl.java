package pl.matadini.sysmusic.server.context.publisher;

import lombok.AllArgsConstructor;
import pl.matadini.sysmusic.server.common.jpa.JpaRepositoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
class PublisherRepositoryImpl implements PublisherRepository {

    EntityManager entityManager;

    @Override
    public Publisher save(Publisher entity) {
        return JpaRepositoryUtil.merge(entity, entityManager);
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        return Optional.ofNullable(JpaRepositoryUtil.read(Publisher.class, id, entityManager));
    }

    @Override
    public void delete(Publisher entity) {
        JpaRepositoryUtil.delete(entity, entityManager);
    }

    @Override
    public List<Publisher> findAll() {
        String sql = "select p from Publisher p order by p.publisherId desc ";
        TypedQuery<Publisher> query = entityManager.createQuery(sql, Publisher.class);
        return query.getResultList();
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Long count() {
        return null;
    }
}
