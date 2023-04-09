package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Customer;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class CustomerCrudRepository extends CrudRepository<Long, Customer> {
    public CustomerCrudRepository(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
