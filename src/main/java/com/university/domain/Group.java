package com.university.domain;

import com.university.dao.Identified;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fordros on 02.04.2016.
 */
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
		if (students == null) {
			students = new ArrayList<>();
		}
        students.add(newStudent);
    }

    public void removeStudent(Student student){
		if (students == null) {
			students = new ArrayList<>();
		}
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

	public void setStudents(List<Student> students) {
		this.students = students;
	}

    @Override
    public String toString(){

        return "Group{" +
                "id='" + id + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }
}
