package com.basicsourcecode.repository;

import com.example.basicbank.basic.entity.BasicEntity;
import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
@MappedSuperclass
public abstract class BasicRepositoryImpl<E extends BasicEntity<P>,P extends Serializable , R extends BasicRepository<E,P>> extends BasicEntityManager<E,P> {
    @Autowired
    protected R repository;

    protected BasicRepositoryImpl(){}

}
