package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Address;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class AddressCrudRepository extends CrudRepository<Long, Address> {
    public AddressCrudRepository(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
