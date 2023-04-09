package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Store;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class StoreCrudRepository extends CrudRepository<Long, Store> {
    public StoreCrudRepository(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
