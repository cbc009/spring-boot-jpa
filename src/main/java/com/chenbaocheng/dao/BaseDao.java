package com.chenbaocheng.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Created by CBC on 2015-04-10 17:20.
 */
@NoRepositoryBean
public interface BaseDao<T, PK extends Serializable> extends CrudRepository<T, PK>, PagingAndSortingRepository<T, PK>, JpaSpecificationExecutor<T> {
}
