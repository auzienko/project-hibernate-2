package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Staff;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class StaffCrudRepository extends CrudRepository<Long, Staff> {
    public StaffCrudRepository(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
