package com.university.dao.impl;


import com.university.dao.AbstractJDBCDao;
import com.university.dao.DaoFactory;
import com.university.domain.Group;
import com.university.domain.Student;
import com.university.exception.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class StudentDaoImpl extends AbstractJDBCDao<Student, Integer> {

    private class PersistStudent extends Student {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, first_name, last_name, contact_information, id_group FROM Student";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Student (first_name, last_name, contact_information, id_group) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Student \n" +
                "SET first_name = ?, last_name  = ?, contact_information = ?, id_group = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Student WHERE id= ?;";
    }

    @Override
    public Student create() throws DaoException {
        Student s = new Student();
        return persist(s);
    }

    public StudentDaoImpl(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(Student.class, "group");
    }

    @Override
    protected List<Student> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Student> result = new LinkedList<Student>();
        try {
            while (rs.next()) {
                PersistStudent student = new PersistStudent();
                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setContactInformation(rs.getString("contact_information"));
                student.setGroup((Group) getDependence(Group.class, rs.getInt("id_group")));
                result.add(student);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Student student) throws DaoException {
        try {
            int groupId = (student.getGroup() == null || student.getGroup().getId() == null) ? -1
                    : student.getGroup().getId();
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getContactInformation());
            statement.setInt(4, groupId);
            statement.setInt(5, student.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Student student) throws DaoException {
        try {
            int groupId = (student.getGroup() == null || student.getGroup().getId() == null) ? -1
                    : student.getGroup().getId();
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getContactInformation());
            statement.setInt(4, groupId);
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
