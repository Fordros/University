package com.university.dao.impl;

import com.university.dao.DaoFactory;
import com.university.dao.GenericDao;
import com.university.domain.*;
import com.university.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DaoFactoryImpl implements DaoFactory<Connection> {

    private String user = "postgres";//Логин пользователя
    private String password = "sa62298";//Пароль пользователя
    private String url = "jdbc:postgresql://localhost:5432/postgres";//URL адрес
    private String driver = "org.postgresql.Driver";//Имя драйвера
    private Map<Class, DaoCreator> creators;


    public Connection getConnection() throws DaoException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DaoException("Connection failed ",e);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Class dtoClass) throws DaoException {
        DaoCreator creator = creators.get(dtoClass);
        Connection connection = getConnection();
        if (creator == null) {
            throw new DaoException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public DaoFactoryImpl() {
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Group.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new GroupDaoImpl(DaoFactoryImpl.this, connection);
            }
        });
        creators.put(Student.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new StudentDaoImpl(DaoFactoryImpl.this, connection);
            }
        });
        creators.put(Classroom.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new ClassroomDaoImpl(DaoFactoryImpl.this, connection);
            }
        });
        creators.put(Lesson.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new LessonDaoImpl(DaoFactoryImpl.this, connection);
            }
        });
        creators.put(Lecturer.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new LecturerDaoImpl(DaoFactoryImpl.this, connection);
            }
        });
    }
}
