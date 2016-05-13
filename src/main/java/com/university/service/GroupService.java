package com.university.service;

import com.university.dao.DaoFactory;
import com.university.dao.impl.DaoFactoryImpl;
import com.university.domain.Group;
import com.university.domain.Student;
import com.university.exception.DaoException;




import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;


public class GroupService extends BaseServlet{

    DaoFactory factory = new DaoFactoryImpl();

	
   public List<Student> getAllStudents() throws DaoException{ 
        List<Student> students =  factory.getDao(Student.class).getAll();
        return students;
   }
   
   public Student findById(Integer id) throws DaoException{
	   Student student = (Student) factory.getDao(Student.class).getByPK(id);
	   return student;
   }
   
   public List<Student> findByGroupNumber(Integer id) throws DaoException{
	   List<Student> students = factory.getDao(Student.class).getAll();
	return null;
   }
   
   public void addNewStudent(Student student) throws DaoException{
        Group group = (Group) factory.getDao(Group.class).create();
        group.setGroupNumber("1234");
        student.setGroup(group);
        student = (Student) factory.getDao(Student.class).persist(student);
        //factory.getDao(Student.class).update(student);
    }

@Override
public void save() throws ServletException, IOException {
	// TODO Auto-generated method stub
	
}

@Override
public void edit(Integer parseInt) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
}

@Override
public void delete(Integer parseInt) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
}

@Override
public void create() throws ServletException, IOException {
	// TODO Auto-generated method stub
	
}

@Override
public void viewAll() throws ServletException, IOException {
	List<Student> students;
	try {
		students = factory.getDao(Student.class).getAll();
		request.setAttribute("students", students);
		request.getRequestDispatcher("error.jsp").forward(request, response);
	} catch (DaoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
   
   

}
