package com.basicsourcecode.service;

import com.example.basicbank.basic.customization.BeanUtilsCustom;
import com.example.basicbank.basic.entity.BasicEntity;
import com.example.basicbank.basic.repository.BasicRepository;
import com.example.basicbank.basic.repository.BasicRepositoryImpl;

import java.io.Serializable;
import java.util.List;

public class BasicServiceImpl<E extends BasicEntity<P>,P extends Serializable , R extends BasicRepository<E,P>> extends BasicRepositoryImpl<E,P,R> implements BasicService<E,P> {

    @Override
    public E saveOrUpdate(P id, E entity) {
        E targetEntity = repository.findById(id).orElse(null);
        if (targetEntity==null)
            return save(entity);

        // map current object to current object database by use (BeanUtils,ModelMapper,mapstruct)
        BeanUtilsCustom.copyDeepProperties(entity,targetEntity,BasicEntity.class);
        return save(targetEntity);
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public List<E> saveList(List<E> entities) {
        if (!entities.isEmpty()){
            return repository.saveAll(entities);
        }
        return null;
    }

    @Override
    public E getById(P id) {
        return  repository.getById(id);
    }

    @Override
    public List<E> findById(P id) {
        return repository.findAllById((Iterable<P>) id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

}
