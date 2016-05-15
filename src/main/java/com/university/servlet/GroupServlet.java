package com.university.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.university.dao.impl.GroupDaoImpl;
import com.university.domain.Group;
import com.university.domain.Student;
import com.university.exception.DaoException;
import com.university.service.AbstaractService;
import com.university.service.GroupService;

/**
 * Servlet implementation class Student
 */
@WebServlet("/group")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/group.jsp";
    private static String LIST_USER = "/addStudent.jsp";

    	GroupService groupService = new GroupService();
    	AbstaractService universityService = new AbstaractService(Group.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="startForward";
        String action = request.getParameter("action");




        if (action.equalsIgnoreCase("delete")){
            try {
            	int id = Integer.parseInt(request.getParameter("id"));
                groupService.delete(id);
                forward = INSERT_OR_EDIT;
				request.setAttribute("students", groupService.getAllStudents());
			} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			}
        } else if (action.equalsIgnoreCase("edit")){
        	try {
	            forward = LIST_USER;
	            int id = Integer.parseInt(request.getParameter("id"));
	            Student student = groupService.findById(id);
	            request.setAttribute("student", student);
        	} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			}
        } else if (action.equalsIgnoreCase("insert")){
        	try {
	            forward = LIST_USER;
	            request.setAttribute("students", groupService.getAllStudents());
	            request.setAttribute("groups", universityService.getAll());
        	} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
        	}
        } else {
        	 try {
            forward = INSERT_OR_EDIT;
				List<Student> students = groupService.getAllStudents();
				request.setAttribute("students", students);
			} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			}
        }

        request.getRequestDispatcher(forward).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding ("UTF-8");

		Student student = new Student();
		try {
		student.setFirstName(request.getParameter("firstName"));
		student.setLastName(request.getParameter("lastName"));
		student.setContactInformation(request.getParameter("contactInformation"));
		String[] option = request.getParameterValues("exampleSelect1");
		Group group = (Group) universityService.findById(Integer.parseInt(option[0]));
		student.setGroup(group);
		groupService.addNewStudent(student);


		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
        try {
			request.setAttribute("students", groupService.getAllStudents());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        view.forward(request, response);
	}
	}


