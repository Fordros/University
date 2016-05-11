package com.university.dao.impl;

import com.university.dao.AbstractJDBCDao;
import com.university.dao.DaoFactory;
import com.university.domain.Classroom;
import com.university.domain.Group;
import com.university.domain.Lecturer;
import com.university.domain.Lesson;
import com.university.exception.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class LessonDaoImpl extends AbstractJDBCDao<Lesson, Integer> {
    private class PersistLesson extends Lesson {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, number, addres FROM Lesson";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Lesson (number, addres) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Lesson \n" +
                "SET number = ?, addres  = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Lesson WHERE id= ?;";
    }

    @Override
    public Lesson create() throws DaoException {
        Lesson lesson = new Lesson();
        return persist(lesson);
    }

    public LessonDaoImpl(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(Lesson.class, "group");
        addRelation(Lesson.class, "professor");
        addRelation(Lesson.class, "classroom");
    }

    @Override
    protected List<Lesson> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Lesson> result = new LinkedList<Lesson>();
        try {
            while (rs.next()) {
                PersistLesson lesson = new PersistLesson();
                lesson.setId(rs.getInt("id"));
                lesson.setGroup((Group) getDependence(Group.class, rs.getString("number")));
                lesson.setProfessor((Lecturer) getDependence(Lecturer.class, rs.getString("lastName")));
                lesson.setClassroom((Classroom) getDependence(Classroom.class, rs.getString("number")));
                lesson.setClassTime(rs.getDate("weekDay"));
                result.add(lesson);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Lesson lesson) throws DaoException {

    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Lesson lesson) throws DaoException {

    }

    protected Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }
}
