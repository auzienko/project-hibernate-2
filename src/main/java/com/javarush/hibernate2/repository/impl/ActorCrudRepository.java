package com.javarush.hibernate2.repository.impl;

import com.javarush.hibernate2.entity.Actor;
import com.javarush.hibernate2.repository.CrudRepository;
import org.hibernate.SessionFactory;

public class ActorCrudRepository extends CrudRepository<Long, Actor> {
    public ActorCrudRepository(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
