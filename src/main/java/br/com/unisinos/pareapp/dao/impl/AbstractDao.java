package br.com.unisinos.pareapp.dao.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.model.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Transactional
@RequiredArgsConstructor
public abstract class AbstractDao<T extends BaseEntity> implements BaseDao<T> {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<T> find(final Integer id) {
        EntityManager entityManager = beginTransaction();
        Optional<T> result = ofNullable(entityManager.find(getType(), id));
        closeTransaction(entityManager);
        return result;
    }

    public Optional<T> find(T entity) {
        if(entity.getId() != null) return find(entity.getId());
        return parameterizedFind(entity);
    }

    protected abstract Optional<T> parameterizedFind(T entity);

    protected abstract Class<T> getType();

    @Override
    public T save(T entity) {
        EntityManager entityManager = beginTransaction();
        T persisted = entityManager.merge(entity);
        closeTransaction(entityManager);
        return persisted;
    }

    protected Optional<T> findBy(Map<String, String> parameters) {
        EntityManager entityManager = beginTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getType());
        Root<T> root = query.from(getType());
        query.select(root);
        parameters.forEach((key, value) -> query.where(
                builder.equal(root.get(key), value)
        ));

        Optional<List<T>> resultList = ofNullable(entityManager.createQuery(query).getResultList());
        Optional<T> result = resultList.map(list -> list.get(0));

        closeTransaction(entityManager);

        return result;
    }

    protected void closeTransaction(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    protected EntityManager beginTransaction() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }

    public T remove(Integer id) {
        EntityManager entityManager = beginTransaction();
        T entity = entityManager.find(getType(), id);
        entityManager.remove(entity);
        closeTransaction(entityManager);
        return entity;
    }
}
