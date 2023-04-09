package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Language;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class LanguageCrudRepository extends CrudRepository<Long, Language> {
    public LanguageCrudRepository(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
