package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Film;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FilmCrudRepository extends CrudRepository<Long, Film> {
    public FilmCrudRepository(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getAnyUnreturnedRental() {
        String queryString = "select u from Film u where u.id not in (select distinct film.id from Inventory)";
        Query<Film> query = getCurrentSession().createQuery(queryString, Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
