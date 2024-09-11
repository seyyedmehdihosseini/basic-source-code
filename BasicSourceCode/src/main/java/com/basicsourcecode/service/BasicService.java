package com.basicsourcecode.service;

import com.example.basicbank.basic.entity.BasicEntity;

import java.io.Serializable;

public interface BasicService<E extends BasicEntity<P>, P extends Serializable> extends BasicRegistry<E,P> , BasicFetch<E,P> {

}
