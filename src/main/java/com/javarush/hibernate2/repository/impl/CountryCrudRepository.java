package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Country;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class CountryCrudRepository extends CrudRepository<Long, Country> {
    public CountryCrudRepository(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
