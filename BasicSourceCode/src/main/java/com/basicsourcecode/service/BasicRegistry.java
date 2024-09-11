package com.basicsourcecode.service;

import com.example.basicbank.basic.entity.BasicEntity;

import java.io.Serializable;
import java.util.List;

public interface BasicRegistry<E extends BasicEntity<P>, P extends Serializable> {
    E saveOrUpdate(P id, E entity);

    E save(E entity);

    List<E> saveList(List<E> entities);

}
