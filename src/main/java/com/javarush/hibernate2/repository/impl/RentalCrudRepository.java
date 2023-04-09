package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Rental;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RentalCrudRepository extends CrudRepository<Long, Rental> {
    public RentalCrudRepository(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental getAnyUnreturnedRental() {
        String queryString = "select u from Rental u where u.returnDate IS NULL";
        Query<Rental> query = getCurrentSession().createQuery(queryString, Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
