package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.FilmText;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class FilmTextCrudRepository extends CrudRepository<Long, FilmText> {
    public FilmTextCrudRepository(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
