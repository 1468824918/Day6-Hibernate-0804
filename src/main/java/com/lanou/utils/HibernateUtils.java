package com.lanou.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by dllo on 17/10/30.
 */
public class HibernateUtils {
//    SessionFactory只能有一个
    private static final SessionFactory SF;

    static{
        Configuration cfg = new Configuration().configure();
        SF = cfg.buildSessionFactory();
    }

    public static Session openSession(){
        return SF.openSession();
    }

    public static <T> T handle(ResultHandler<T> handler){
        Session sess = openSession();
        Transaction tr = sess.beginTransaction();

        T result = handler.handle(sess);

        tr.commit();
        sess.close();
        return result;

    }

    public interface ResultHandler<T>{
        T handle(Session sess);
    }
}
