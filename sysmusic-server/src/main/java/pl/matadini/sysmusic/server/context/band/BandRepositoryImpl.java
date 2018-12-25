package pl.matadini.sysmusic.server.context.band;

import lombok.AllArgsConstructor;
import pl.matadini.sysmusic.server.common.jpa.JpaRepositoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
class BandRepositoryImpl implements BandRepository {

    EntityManager entityManager;

    @Override
    public Band save(Band entity) {
        return JpaRepositoryUtil.merge(entity, entityManager);
    }

    @Override
    public Optional<Band> findById(Long id) {
        return Optional.ofNullable(JpaRepositoryUtil.read(Band.class, id, entityManager));
    }

    @Override
    public void delete(Band entity) {
        JpaRepositoryUtil.delete(entity, entityManager);
    }

    @Override
    public List<Band> findAll() {
        String sql = "select b from Band b order by b.bandId";
        TypedQuery<Band> query = entityManager.createQuery(sql, Band.class);
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
