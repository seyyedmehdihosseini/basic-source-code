package com.basicsourcecode.repository;

import com.example.basicbank.basic.entity.BasicEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;

import java.io.Serializable;

public class BasicEntityManager<E extends BasicEntity<P>,P extends Serializable> {
    @PersistenceContext
    private EntityManager entityManager;

    protected BasicEntityManager(){}

    protected EntityManager getEntityManager() {
        return entityManager;
    }
    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

}
