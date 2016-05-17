package com.univestity.entity;


import java.util.GregorianCalendar;
import java.util.List;
import com.university.domain.*;
import org.junit.Test;


public class UniversityTest {
    GregorianCalendar calen1 = new GregorianCalendar(2016, 3, 6, 11, 10);
    GregorianCalendar calen2 = new GregorianCalendar(2016, 3, 8, 14, 20);
    GregorianCalendar calen3 = new GregorianCalendar(2016, 3, 9, 16, 40);
    Group group = new Group(1, "F1");
    Group group1 = new Group(2, "A2");
    Student student = new Student(1,"Rost","Ivashchenko","0675404060", group);
    Student student1 = new Student(2, "Ivav", "Ivanov", "0671231231", group1);
    Classroom classroom = new Classroom("123","Telmana 28");
    Lecturer lecturer = new Lecturer(1,"Sergey","Nemchinskiy","0671234567", "FULL_PROFESSOR");
    Lecturer lecturer2 = new Lecturer(2,"Dmitriy","Gorskiy","0677654321", "FULL_PROFESSOR");
    Lesson lesson  = new Lesson(group,lecturer,classroom,calen1.getTime(), "SEMINAR");
    Lesson lesson2 = new Lesson(group1, lecturer2, classroom, calen2.getTime(), "LABORATORY_CLASS");
    University university = new University("KPI","Kyiv","0441234567");

    @Test
    public void addLesson(){
        System.out.println("addLesson");
        university.addGroup(group);
        university.addGroup(group1);
        university.addLecturer(lecturer);
        university.addLecturer(lecturer2);
        group.addStudent(student);
        group.addStudent(student1);
        for(int i = 0; i <5; i++){
            university.addTimetable(lesson);
            university.addTimetable(lesson2);
        }
        System.out.println(university.getTimetable().toString());
    }

    @Test
    public void getTimetableForLecturer(){
        System.out.println("getTimetableForLecturer");
        university.addGroup(group);
        university.addGroup(group1);
        university.addLecturer(lecturer);
        university.addLecturer(lecturer2);
        group.addStudent(student);
        group.addStudent(student1);
        for(int i = 0; i <5; i++){
            university.addTimetable(lesson);
            university.addTimetable(lesson2);
        }
        List<Lesson> lessons = university.getTimetableForLecturer(lecturer,calen3.getTime());
        System.out.println(lessons);
    }

    @Test
    public void getTimetableForStudent(){
        System.out.println("getTimetableForStudent");
        university.addGroup(group);
        university.addGroup(group1);
        university.addLecturer(lecturer);
        university.addLecturer(lecturer2);
        group.addStudent(student);
        group.addStudent(student1);
        for(int i = 0; i <5; i++){
            university.addTimetable(lesson);
            university.addTimetable(lesson2);
        }
        List<Lesson> lessons = university.getTimetableForStudents(student1,
                calen3.getTime());
        System.out.println(lessons);
    }

    @Test
    public void moveStudentTest(){
        System.out.println("moveStudentTest");
        university.addGroup(group);
        university.addGroup(group1);
        group.addStudent(student);
        group1.addStudent(student1);
        System.out.println(student.getGroup().getGroupNumber());
        university.moveStudent(student,group1);
        System.out.println(group.getStudents().size());
        System.out.println(group1.getStudents().size());
        System.out.println(student.getGroup().getGroupNumber());

    }

}
