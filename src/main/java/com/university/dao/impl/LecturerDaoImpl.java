package com.university.dao.impl;

import com.university.dao.AbstractJDBCDao;
import com.university.dao.DaoFactory;
import com.university.domain.Lecturer;
import com.university.domain.Lesson;
import com.university.exception.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class LecturerDaoImpl extends AbstractJDBCDao<Lecturer, Integer> {

    private class PersistLecturer extends Lecturer {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, first_name, last_name, contact_information, qualification FROM Lecturer";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Lecturer (first_name, last_name, contact_information, qualification) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Lecturer \n" +
                "SET first_name = ?, last_name  = ?, contact_information = ?, qualification = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Lecturer WHERE id= ?;";
    }

    @Override
    public Lecturer create() throws DaoException {
        Lecturer lecturer = new Lecturer();
        return persist(lecturer);
    }

    public LecturerDaoImpl(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    protected List<Lecturer> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Lecturer> result = new LinkedList<Lecturer>();
        try {
            while (rs.next()) {
                PersistLecturer lecturer = new PersistLecturer();
                lecturer.setId(rs.getInt("id"));
                lecturer.setFirstName(rs.getString("first_name"));
                lecturer.setLastName(rs.getString("last_name"));
                lecturer.setContactInformation(rs.getString("contact_information"));
                lecturer.setQualification(rs.getString("qualification"));
                result.add(lecturer);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Lecturer lecturer) throws DaoException {
        try {
            statement.setString(1, lecturer.getFirstName());
            statement.setString(2, lecturer.getLastName());
            statement.setString(3, lecturer.getContactInformation());
            statement.setString(4, lecturer.getQualification());
            statement.setInt(5, lecturer.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Lecturer lecturer) throws DaoException {
        try {
            statement.setString(1, lecturer.getFirstName());
            statement.setString(2, lecturer.getLastName());
            statement.setString(3, lecturer.getContactInformation());
            statement.setString(4, lecturer.getQualification());
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
