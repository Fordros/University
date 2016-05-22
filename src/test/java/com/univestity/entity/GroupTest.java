package com.univestity.entity;

import com.university.domain.entity.Group;
import com.university.domain.entity.Student;
import org.junit.Test;


public class GroupTest {

    Group group = new Group(1, "F1");
    Group group1 = new Group(2, "F2");
    Student student = new Student(1,"Rost","Ivashchenko","0675404060", group);
    Student student1 = new Student(1,"Volodymyr","Ivashchenko","0675404061", group);

    @Test
    public void addStudentTest(){
        group.addStudent(student);
        System.out.println(group);
    }
    @Test
    public void removeStudentTest(){
        group.addStudent(student);
        group.addStudent(student1);
        System.out.println(group);
        group.removeStudent(student);
        System.out.println(group);
    }

}
