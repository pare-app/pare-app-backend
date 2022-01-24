package br.com.unisinos.pareapp.dao.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.model.dto.BaseDto;
import br.com.unisinos.pareapp.model.entity.BaseEntity;
import br.com.unisinos.pareapp.model.entity.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Transactional
@RequiredArgsConstructor
public abstract class AbstractDao<T extends BaseEntity> implements BaseDao<T> {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    protected final Class<T> type;

    @Override
    public Optional<T> find(final Integer id) {
        EntityManager entityManager = begingTransaction();
        T result = entityManager.find(type, id);
        closeTransaction(entityManager);
        return ofNullable(result);
    }

    @Override
    public void save(T entity) {
        EntityManager entityManager = begingTransaction();
        entityManager.persist(entity);
        closeTransaction(entityManager);
    }

    protected Optional<T> findBy(Map<String, String> parameters) {
        EntityManager entityManager = begingTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root);
        parameters.forEach((key, value) -> query.where(
                builder.equal(root.get(key), value)
        ));
        T result = entityManager.createQuery(query).getResultList().get(0);

        closeTransaction(entityManager);

        return ofNullable(result);
    }

    protected void closeTransaction(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    protected EntityManager begingTransaction() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }
}
