package com.lanou.dao.impl;

import com.lanou.dao.BaseDao;
import com.lanou.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by dllo on 17/10/30.
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
    @Override
    public T save(T bean) {
        Session sess = HibernateUtils.openSession();
        Transaction tr = sess.beginTransaction();
        sess.save(bean);
        tr.commit();
        sess.close();
        return bean;
    }

    @Override
    public T delete(T bean) {
        Session sess = HibernateUtils.openSession();
        Transaction tr = sess.beginTransaction();
        sess.delete(bean);
        tr.commit();
        sess.close();
        return bean;
    }

    @Override
    public T update(T bean) {
        Session sess = HibernateUtils.openSession();
        Transaction tr = sess.beginTransaction();
        sess.update(bean);
        tr.commit();
        sess.close();
        return bean;
    }

    @Override
    public List<T> findAll(Class<T> clazz) {
        Session sess = HibernateUtils.openSession();
        Transaction tr = sess.beginTransaction();

        Query<T> query;
        try {
            query = sess.createQuery("from " + clazz.getSimpleName(), clazz);
            tr.commit();
            return query.list();
        } finally {
            sess.close();
        }
    }
}
