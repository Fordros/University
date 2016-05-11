package com.university.domain;

import com.university.dao.Identified;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Lesson implements Identified<Integer> {
    private Integer id;
    private Group group;
    private Lecturer professor;
    private Classroom classroom;
    private Date lessonTime;
    StudiesTypes studiesTypes;

    public Lesson(){

    }

    public Lesson(Group group, Lecturer professor, Classroom classroom, Date classTime, StudiesTypes studiesTypes){
        this.setGroup(group);
        this.setProfessor(professor);
        this.setClassroom(classroom);
        this.setClassTime(classTime);
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Lecturer getProfessor() {
        return professor;
    }

    public void setProfessor(Lecturer professor) {
        this.professor = professor;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Date getClassTime() {
        return lessonTime;
    }

    public void setClassTime(Date classTime) {
        this.lessonTime = classTime;
    }

    @Override
    public String toString(){
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return "Lesson{" +
                "group='" + group.getGroupNumber() + '\'' +
                ", professor='" + professor.getFirstName() + '\'' +
                ", classroom='" + classroom.getNumber() + '\'' +
                ", lessonTime='" + format.format(lessonTime) + '\'' +
                '}' + '\n';
    }


}
