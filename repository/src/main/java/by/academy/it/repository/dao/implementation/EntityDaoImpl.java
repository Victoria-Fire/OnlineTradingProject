package by.academy.it.repository.dao.implementation;

import by.academy.it.repository.dao.interfaces.EntityDao;
import by.academy.it.repository.dao.exception.EntityDaoException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EntityDaoImpl<T> implements EntityDao<T> {
    protected final EntityManager entityManager;
    private final Class<T> clazz;

    public EntityDaoImpl(final EntityManager em, final Class<T> clazz) {
        this.entityManager = em;
        this.clazz = clazz;
    }

    @Override
    public T findById(final Integer id) {
        T entity = null;
        try {
            entity = entityManager.find(clazz, id);
        } catch (IllegalArgumentException e) {
            throw new EntityDaoException(e);
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> rootEntry = criteriaQuery.from(clazz);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(final T t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new EntityDaoException(e);
        }
    }

    @Override
    public void save(final List<T> list) {
        try {
            entityManager.getTransaction().begin();
            list.forEach(entityManager::persist);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new EntityDaoException(e);
        }
    }

    @Override
    public void update(final T t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new EntityDaoException(e);
        }
    }

    @Override
    public void delete(final Integer id) {
        try {
            T entity = entityManager.find(clazz, id);
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new EntityDaoException(e);
        }
    }

    @Override
    public void closeDao() {
        entityManager.close();
    }
}

