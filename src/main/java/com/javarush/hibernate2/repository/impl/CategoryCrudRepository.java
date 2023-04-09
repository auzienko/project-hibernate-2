package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Category;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class CategoryCrudRepository extends CrudRepository<Long, Category> {
    public CategoryCrudRepository(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
