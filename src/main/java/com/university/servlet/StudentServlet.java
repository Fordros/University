package com.university.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.university.dao.DaoFactory;
import com.university.dao.impl.DaoFactoryImpl;
import com.university.domain.Student;
import com.university.exception.DaoException;

/**
 * Servlet implementation class Student
 */
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    	DaoFactory factory = new DaoFactoryImpl();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Student> students =  factory.getDao(Student.class).getAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("student.jsp").forward(request, response);
	}


}
