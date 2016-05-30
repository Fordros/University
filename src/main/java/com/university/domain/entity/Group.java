package com.university.domain.entity;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "university_groups")
public class Group implements Serializable  {
	

	private static final long serialVersionUID = 1L;

    private Integer id;
    private String groupNumber;
	private List<Student> students;


    public Group(){

    }

	public Group(Integer id, String groupNumber) {
        this.setId(id);
        this.setGroupNumber(groupNumber);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, insertable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "GROUP_NUMBER", unique = true, nullable = false, length = 10)
    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    public List<Student> getStudents() {
        return students;
    }


    public void setStudents(List<Student> students) {
		this.students = students;
	}
    
    public void addStudent(Student newStudent){
		students = new ArrayList<>();
    students.add(newStudent);
}

public void removeStudent(Student student){
		students = new ArrayList<>();
    students.remove(student);
}

	/*@Override
    public String toString(){

        return "Group{" +
                "id='" + id + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }*/
}
