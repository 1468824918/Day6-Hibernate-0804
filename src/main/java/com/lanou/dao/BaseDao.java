package com.lanou.dao;

import java.util.List;

/**
 * Created by dllo on 17/10/30.
 */
public interface BaseDao<T> {
    T save(T bean);
    T delete(T bean);
    T update(T bean);
    List<T> findAll(Class<T> clazz);
}
