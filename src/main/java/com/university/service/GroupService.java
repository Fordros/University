package com.university.service;

import com.university.dao.DaoFactory;
import com.university.dao.impl.DaoFactoryImpl;
import com.university.domain.Group;
import com.university.domain.Student;
import com.university.exception.DaoException;



import java.io.Serializable;
import java.util.List;


public class GroupService{

    DaoFactory factory = new DaoFactoryImpl();

	
   public List<Student> getAllStudents() throws DaoException{ 
        List<Student> students =  factory.getDao(Student.class).getAll();
        return students;
   }
   
   public Student findById(Serializable key) throws DaoException{
	   Student student = (Student) factory.getDao(Student.class).getByPK(key);
	   return student;
   }
   
   
   public void addNewStudent(Student student) throws DaoException{
        Group group = (Group) factory.getDao(Group.class).create();
        group.setGroupNumber("1234");
        student.setGroup(group);
        student = (Student) factory.getDao(Student.class).persist(student);
        //factory.getDao(Student.class).update(student);
    }
   
   

}
