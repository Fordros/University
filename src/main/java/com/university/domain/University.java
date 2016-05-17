package com.university.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class University {
    private String name;
    private String address;
    private String phone;
    private List<Lesson> timetable = new ArrayList<Lesson>();
    private List<Lecturer> lecturers = new ArrayList<Lecturer>();
    private List<Group> groups = new ArrayList<Group>();

    public University(String name, String address, String phone){
        this.setName(name);
        this.setAddress(address);
        this.setPhone(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Lesson> getTimetable() {
        return timetable;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void addTimetable(Lesson lesson){
        this.timetable.add(lesson);
    }

    public void addGroup(Group group){
        this.groups.add(group);
    }

    public void addLecturer(Lecturer lecturer){
        this.lecturers.add(lecturer);
    }

    public void moveStudent(Student student, Group newGroup){
        Group oldGroup;
        for ( Group g : groups) {
            if (g.equals(student.getGroup())){
                oldGroup = g;
                oldGroup.removeStudent(student);
            }
        }
        student.setGroup(newGroup);
        newGroup.addStudent(student);

    }

    public List<Lesson> getTimetableForLecturer(Lecturer lecturer, Date date){
        List<Lesson> result = new ArrayList<Lesson>();
        Date today = new Date();
        for ( Lesson l : timetable) {
            if (l.getProfessor().equals(lecturer) && l.getLessonTime().after(today) && l.getLessonTime().before(date)){
                result.add(l);
            }
        }
        return result;
    }

    public List<Lesson> getTimetableForStudents(Student student, Date date){
        List<Lesson> result = new ArrayList<Lesson>();
        Date today = new Date();
        for ( Lesson l : timetable) {
            if (l.getGroup().equals(student.getGroup()) && l.getLessonTime().after(today) && l.getLessonTime().before(date)){
                result.add(l);
            }
        }
        return result;
    }

}
