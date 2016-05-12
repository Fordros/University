package com.university.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.university.domain.Student;
import com.university.exception.DaoException;
import com.university.service.GroupService;

/**
 * Servlet implementation class Student
 */
@WebServlet("/group")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    	GroupService groupService = new GroupService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			try {
				List<Student> students =  groupService.getAllStudents();
				request.setAttribute("students", students);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		request.getRequestDispatcher("group.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding ("UTF-8");
		List<Student> students =  groupService.(request.getParameter("groupNumber"));
		
		request.setAttribute("students", students);

		request.getRequestDispatcher("group.jsp").forward(request, response);
	}
	}

}
