package com.university.dao.impl;

import com.university.dao.AbstractJDBCDao;
import com.university.dao.DaoFactory;
import com.university.domain.Classroom;
import com.university.exception.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class ClassroomDaoImpl extends AbstractJDBCDao<Classroom, Integer> {
    private class PersistClassroom extends Classroom {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, number, addres FROM Classroom";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Classroom (number, addres) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Classroom \n" +
                "SET number = ?, addres  = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Classroom WHERE id= ?;";
    }

    @Override
    public Classroom create() throws DaoException {
        Classroom classroom = new Classroom();
        return persist(classroom);
    }

    public ClassroomDaoImpl(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    protected List<Classroom> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Classroom> result = new LinkedList<Classroom>();
        try {
            while (rs.next()) {
                PersistClassroom classroom = new PersistClassroom();
                classroom.setId(rs.getInt("id"));
                classroom.setNumber(rs.getString("number"));
                classroom.setAddress(rs.getString("addres"));
                result.add(classroom);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Classroom classroom) throws DaoException {
        try {
            statement.setString(1, classroom.getNumber());
            statement.setString(2, classroom.getAddress());
            statement.setInt(3, classroom.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Classroom classroom) throws DaoException {
        try {
            statement.setString(1, classroom.getNumber());
            statement.setString(2, classroom.getAddress());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    protected Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }
}
