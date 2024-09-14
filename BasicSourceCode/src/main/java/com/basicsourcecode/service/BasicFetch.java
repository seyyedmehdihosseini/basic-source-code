package com.basicsourcecode.service;

import com.basicsourcecode.entity.BasicEntity;

import java.io.Serializable;
import java.util.List;

public interface BasicFetch<E extends BasicEntity<P>, P extends Serializable> {
    @Deprecated
    E getById(P id);

    List<E> findById(P id);

    List<E> findAll();

}
