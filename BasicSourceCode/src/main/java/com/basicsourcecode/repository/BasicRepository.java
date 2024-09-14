package com.basicsourcecode.repository;

import com.basicsourcecode.entity.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface BasicRepository<E extends BasicEntity<P>,P extends Serializable> extends JpaRepository<E,P>
        , PagingAndSortingRepository<E,P> , CrudRepository<E,P> {

}
