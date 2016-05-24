package com.university.dao.impl;

import com.university.dao.DaoFactory;
import com.university.dao.GenericDao;
import com.university.domain.entity.*;
import com.university.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;


public class DaoFactoryImpl implements DaoFactory<Connection> {

    private Map<Class, DaoCreator> creators;
    private InitialContext ctx;
    final static Logger logger = Logger.getLogger(DaoFactoryImpl.class);
        
    public Connection getConnection() throws DaoException {
        Connection connection = null;
        try {
			try {
				Context context = (Context) ctx.lookup("java:comp/env");
				DataSource ds = (DataSource)context.lookup("jdbc/root");
				connection = ds.getConnection();
			} catch (NamingException e) {
				logger.error("NamingException", e);
			}
        } catch (SQLException e) {
        	logger.error("Connection failed ", e);
            throw new DaoException("Connection failed ", e);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Class dtoClass) throws DaoException {
        DaoCreator creator = creators.get(dtoClass);
        Connection connection = getConnection();
        if (creator == null) {
        	logger.error("Dao object for " + dtoClass + " not found.");
            throw new DaoException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public DaoFactoryImpl(){
    	try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			logger.error("NamingException", e);
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
