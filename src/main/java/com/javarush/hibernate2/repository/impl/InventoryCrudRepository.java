package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Inventory;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class InventoryCrudRepository extends CrudRepository<Long, Inventory> {
    public InventoryCrudRepository(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
