package com.university.service;

import com.university.dao.DaoFactory;
import com.university.dao.impl.DaoFactoryImpl;
import com.university.domain.Group;
import com.university.domain.Student;
import com.university.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Fordros on 30.04.2016.
 */
public class StudentTest {

    public static void main(String[] args) throws DaoException, SQLException {
        DaoFactory<Connection> factory = new DaoFactoryImpl();
        Connection connection;
        connection = factory.getConnection();
        //connection.setAutoCommit(false);

        List<Student> students =  factory.getDao(connection, Student.class).getAll();
        System.out.println(students);

/*        List<Group> groups =  factory.getDao(connection, Group.class).getAll();
        System.out.println(groups);

        Student student = new Student();
        student.setFirstName("ROST");
        student.setLastName("Ivashchenko");
        student.setContactInformation("KREMEN");
        Group group = (Group) factory.getDao(connection, Group.class).create();
        group.setGroupNumber("1234");
        student.setGroup(group);
        student = (Student) factory.getDao(connection, Student.class).persist(student);
        factory.getDao(connection, Student.class).update(student);

        students =  factory.getDao(connection, Student.class).getAll();
        System.out.println(students);*/

        //connection.rollback();
        connection.close();
    }

}
