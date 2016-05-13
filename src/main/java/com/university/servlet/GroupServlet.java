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
	private static String INSERT_OR_EDIT = "/group.jsp";
    private static String LIST_USER = "/listGroup.jsp";

    	GroupService groupService = new GroupService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="startForward";
        String action = request.getParameter("action");

		
		
		
        if (action.equalsIgnoreCase("delete")){
            try {
            	int id = Integer.parseInt(request.getParameter("id"));
                groupService.delete(id);
                forward = LIST_USER;
				request.setAttribute("students", groupService.getAllStudents());
			} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			}    
        } else if (action.equalsIgnoreCase("edit")){
        	try {
	            forward = INSERT_OR_EDIT;
	            int id = Integer.parseInt(request.getParameter("id"));
	            Student student = groupService.findById(id);
	            request.setAttribute("student", student);
        	} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			}   
        } else if (action.equalsIgnoreCase("listUser")){
        	try {
	            forward = LIST_USER;
	            request.setAttribute("students", groupService.getAllStudents());
        	} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
        	}   
        } else {
            forward = INSERT_OR_EDIT;
        }
        
        request.getRequestDispatcher(forward).forward(request, response);	
       
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding ("UTF-8");
		/*List<Student> students =  groupService.(request.getParameter("groupNumber"));
		
		request.setAttribute("students", students);

		request.getRequestDispatcher("group.jsp").forward(request, response);*/
	}
	}


