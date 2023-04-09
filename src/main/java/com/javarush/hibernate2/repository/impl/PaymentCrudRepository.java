package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Payment;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class PaymentCrudRepository extends CrudRepository<Long, Payment> {
    public PaymentCrudRepository(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
