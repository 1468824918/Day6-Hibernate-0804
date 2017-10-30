package com.lanou.dao.impl;

import com.lanou.dao.BaseDao;
import com.lanou.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by dllo on 17/10/30.
 */
public class BasesDaoImpl<T> implements BaseDao<T>{
    @Override
    public T save(T bean) {
        return HibernateUtils.handle(new HibernateUtils.ResultHandler<T>() {
            @Override
            public T handle(Session sess) {
                sess.save(bean);
                return bean;
            }
        });
    }

    @Override
    public T delete(T bean) {
        return HibernateUtils.handle(new HibernateUtils.ResultHandler<T>() {
            @Override
            public T handle(Session sess) {
                sess.delete(bean);
                return bean;
            }
        });
    }

    @Override
    public T update(T bean) {
        return HibernateUtils.handle(new HibernateUtils.ResultHandler<T>() {
            @Override
            public T handle(Session sess) {
                sess.update(bean);
                return bean;
            }
        });
    }

    @Override
    public List<T> findAll(Class<T> clazz) {
        return HibernateUtils.handle(new HibernateUtils.ResultHandler<List<T>>() {
            @Override
            public List<T> handle(Session sess) {
                List<T> list = sess.createQuery("from " + clazz.getSimpleName(), clazz).list();
                return list;
            }
        });
    }
}
