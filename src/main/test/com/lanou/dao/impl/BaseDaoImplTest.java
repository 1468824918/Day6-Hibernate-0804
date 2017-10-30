package com.lanou.dao.impl;

import com.lanou.dao.BaseDao;
import com.lanou.domain.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dllo on 17/10/30.
 */
public class BaseDaoImplTest {

    private BaseDao<Student> dao;

    @Before
    public void init(){
        dao = new BaseDaoImpl<>();
    }
    @Test
    public void test(){
//        Student s1 = new Student("BC","男",15);
//        dao.save(s1);
//        Student s2 = new Student(7,"","",12);
//        dao.delete(s2);
//        Student s3 = new Student(3,"QQ","女",19);
//        dao.update(s3);

        dao.findAll(Student.class).forEach(System.out::println);
    }
}