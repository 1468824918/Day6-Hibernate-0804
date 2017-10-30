package com.lanou.domain;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dllo on 17/10/30.
 */
public class StudentTest {

    private Session sess;
    private Transaction trans;

    //该注解标记的方法会在测试方法之前执行
    @Before
    public void init(){
        System.out.println("---init---");

        //1.先加载配置文件
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        //2.通过Configuration 对象创建sessionFactory对象
        SessionFactory sf = cfg.buildSessionFactory();

        //3.打开会话
        sess = sf.openSession();

        //通过ession对象开启事务
        trans = sess.beginTransaction();


    }

    //标记某个方法为测试方法
    @Test
    public void save(){
        System.out.println("---save---");

        Student zw = new Student( 2,"ZW","男",15);
        //将实体类对象传入方法中
        //插入方法
        sess.save(zw);
        //会根据id自行选择是插入方法
//        sess.saveOrUpdate(zw);
    }

    //删除操作
    @Test
    public void delete(){
        System.out.println("---删除---");
        Student s = new Student(2,"小明","男",12);
        //删除的时候只看id,与其他的无关
        sess.delete(s);
//        sess.update(s);
    }

    @Test
    public void query(){
        System.out.println("---查询---");

//        List<Student> list1 = sess.createQuery("from Student", Student.class).list();
//        System.out.println("list1="+list1);

        Query<Student> query = sess.createQuery("from Student",Student.class);
        System.out.println("query="+query);

        //查询所有数据
        List<Student> list = query.list();
        List<Student> students = query.getResultList();
        System.out.println("list="+list);

        list.forEach(System.out::println);

        //查询出一条数据
//        Student s = query.getSingleResult();
    }

    @Test
    public void status(){
        Student s = sess.get(Student.class, 1);
        //把对象改为游离状态
        //此时改变实体类对象的属性,不会对表产生影响
        sess.evict(s);
        sess.lock(s, LockMode.UPGRADE_NOWAIT);
        s.setName("小明123");
        s.setGender("男123");
    }



    //标记的方法会在测试方法之后执行
    @After
    public void destroy(){
        System.out.println("---destroy---");

        //提交事务
        trans.commit();
        //关闭会话
        sess.close();
    }
}