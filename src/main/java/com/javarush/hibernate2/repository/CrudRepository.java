package com.javarush.hibernate2.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
public abstract class CrudRepository<I extends Serializable, T> {

    private final Class<T> clazz;
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Query<T> getListQuery() {
        String queryString = String.format("select u from %s u", clazz.getName());
        return getCurrentSession().createQuery(queryString, clazz);
    }

    public T getById(I id) {
        return getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int offset, int count) {
        return getListQuery()
                .setFirstResult(offset)
                .setMaxResults(count)
                .getResultList();
    }

    public List<T> findAll() {
        return getListQuery().list();
    }

    public T save(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    void deleteById(I id) {
        T entity = getById(id);
        delete(entity);
    }
}
