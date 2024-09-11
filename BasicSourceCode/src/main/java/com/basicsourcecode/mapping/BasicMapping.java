package com.basicsourcecode.mapping;

import com.example.basicbank.basic.entity.BasicEntity;

import java.io.Serializable;
import java.util.List;

public interface BasicMapping<E extends BasicEntity<P>, P extends Serializable, D extends Serializable> {

    E convertDtoToEntity(D dto);
    List<E> convertDtoToEntity(List<D> dto);

    D convertEntityToDto(E entity);
    List<D> convertEntityToDto(List<E> entity);

}
