package com.university.domain.entity;

import com.university.dao.Identified;

import java.util.ArrayList;
import java.util.List;


public class Group implements Identified<Integer> {
    private Integer id;
    private String groupNumber;
	private List<Student> students;

    public Group(){

    }



	public Group(Integer id, String groupNumber) {
        this.setId(id);
        this.setGroupNumber(groupNumber);
    }


    public void addStudent(Student newStudent){
			students = new ArrayList<>();
        students.add(newStudent);
    }

    public void removeStudent(Student student){
			students = new ArrayList<>();
        students.remove(student);
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public List<Student> getStudents() {
        return students;
    }


    @Override
    public String toString(){

        return "Group{" +
                "id='" + id + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }
}
