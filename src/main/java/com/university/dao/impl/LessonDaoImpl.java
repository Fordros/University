package com.university.dao.impl;

import com.university.dao.AbstractJDBCDao;
import com.university.dao.DaoFactory;
import com.university.domain.entity.Classroom;
import com.university.domain.entity.Group;
import com.university.domain.entity.Lecturer;
import com.university.domain.entity.Lesson;
import com.university.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Calendar;
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
        return "SELECT id, id_group, id_lecturer, id_classroom, lessontime, studiestypes FROM Lesson";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Lesson (id_group, id_lecturer, id_classroom, lessontime, studiestypes) \n" +
                "VALUES (?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Lesson \n" +
                "SET id_group = ?, id_lecturer  = ?, id_classroom  = ?, lessontime  = ?, studiestypes  = ?,  \n" +
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
        addRelation(Lesson.class, "classroom");
        addRelation(Lesson.class, "lecturer");
        
    }

    @Override
    protected List<Lesson> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Lesson> result = new LinkedList<Lesson>();
        try {
            while (rs.next()) {
                PersistLesson lesson = new PersistLesson();
                lesson.setId(rs.getInt("id"));
                lesson.setGroup((Group) getDependence(Group.class, rs.getInt("id_group")));
                lesson.setLecturer((Lecturer) getDependence(Lecturer.class, rs.getInt("id_lecturer")));
                lesson.setClassroom((Classroom) getDependence(Classroom.class, rs.getInt("id_classroom")));
                lesson.setLessonTime(rs.getTimestamp("lessontime"));
                lesson.setStudiesTypes(rs.getString("studiesTypes"));
                result.add(lesson);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Lesson lesson) throws DaoException {
    	try {
            int groupId = (lesson.getGroup() == null || lesson.getGroup().getId() == null) ? -1
                    : lesson.getGroup().getId();
            int lecturerId = (lesson.getLecturer() == null || lesson.getLecturer().getId() == null) ? -1
                    : lesson.getLecturer().getId();
            int classroomId = (lesson.getClassroom() == null || lesson.getClassroom().getId() == null) ? -1
                    : lesson.getClassroom().getId();
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(lesson.getLessonTime());
            
            Timestamp sqlDate =  new Timestamp(lesson.getLessonTime().getTime());
            statement.setInt(1, groupId);
            statement.setInt(2, lecturerId);
            statement.setInt(3, classroomId);
            statement.setDate(4,  (Date) cal.getTime());
            statement.setString(5, lesson.getStudiesTypes());
            statement.setInt(6, lesson.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Lesson lesson) throws DaoException {
    	try {
            int groupId = (lesson.getGroup() == null || lesson.getGroup().getId() == null) ? -1
                    : lesson.getGroup().getId();
            int lecturerId = (lesson.getLecturer() == null || lesson.getLecturer().getId() == null) ? -1
                    : lesson.getLecturer().getId();
            int classroomId = (lesson.getClassroom() == null || lesson.getClassroom().getId() == null) ? -1
                    : lesson.getClassroom().getId();
            

            Timestamp sqlDate =  new Timestamp(lesson.getLessonTime().getTime());
            statement.setInt(1, groupId);
            statement.setInt(2, lecturerId);
            statement.setInt(3, classroomId);
            statement.setTimestamp(4, sqlDate);
            statement.setString(5, lesson.getStudiesTypes());
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
