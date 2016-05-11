package com.univestity.entity;

import com.university.dao.DaoFactory;
import com.university.dao.GenericDao;
import com.university.dao.Identified;
import com.university.dao.impl.DaoFactoryImpl;
import com.university.domain.Group;
import com.university.domain.Student;
import com.university.exception.DaoException;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;


public class DaoImplTest extends GenericDaoTest<Connection> {

    private Connection connection;

    private GenericDao dao;

    private static final DaoFactory<Connection> factory = new DaoFactoryImpl();

    @Parameterized.Parameters
    public static Collection getParameters() {
        return Arrays.asList(new Object[][]{
                {Group.class, new Group()},
                {Student.class, new Student()}
        });
    }

    @Before
    public void setUp() throws DaoException, SQLException {
        connection = factory.getConnection();
        connection.setAutoCommit(false);
        dao = factory.getDao(daoClass);
    }

    @After
    public void tearDown() throws SQLException {
        context().rollback();
        context().close();
    }

    @Override
    public GenericDao dao() {
        return dao;
    }

    @Override
    public Connection context() {
        return connection;
    }

    public DaoImplTest(Class clazz, Identified<Integer> notPersistedDto) {
        super(clazz, notPersistedDto);
    }
}
