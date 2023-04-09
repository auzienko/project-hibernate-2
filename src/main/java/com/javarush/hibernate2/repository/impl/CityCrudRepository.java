package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.City;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class CityCrudRepository extends CrudRepository<Long, City> {
    public CityCrudRepository(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
