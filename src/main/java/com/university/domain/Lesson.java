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
    private String studiesTypes;

    public Lesson(){

    }



	public Lesson(Group group, Lecturer professor, Classroom classroom, Date lessonTime, String studiesTypes){
        this.setGroup(group);
        this.setProfessor(professor);
        this.setClassroom(classroom);
        this.setLessonTime(lessonTime);
        this.setStudiesTypes(studiesTypes);
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

    public Date getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(Date lessonTime) {
		this.lessonTime = lessonTime;
	}

	public String getStudiesTypes() {
		return studiesTypes;
	}

	public void setStudiesTypes(String studiesTypes) {
		this.studiesTypes = studiesTypes;
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
