package com.university.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.domain.entity.Group;
import com.university.domain.entity.Student;
import com.university.domain.service.AbstaractService;

@WebServlet("/group")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/group.jsp";
    private static String LIST_USER = "/addStudent.jsp";
    final static Logger logger = Logger.getLogger(GroupServlet.class);
    	AbstaractService studentService = new AbstaractService(Student.class);
    	AbstaractService groupService = new AbstaractService(Group.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="startForward";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Delete student by id" + id);
			studentService.delete(id);
			forward = INSERT_OR_EDIT;
			request.setAttribute("students", studentService.getAll());
        } else if (action.equalsIgnoreCase("find")){
        	forward = INSERT_OR_EDIT;
			int id = Integer.parseInt(request.getParameter("id"));
			logger.info("Find student by id" + id);
			Student student = (Student) studentService.findById(id);
			request.setAttribute("students", student);
        } else if (action.equalsIgnoreCase("insert")){
        	forward = LIST_USER;
			request.setAttribute("students", studentService.getAll());
			request.setAttribute("groups", groupService.getAll());
        } else {
        	 forward = INSERT_OR_EDIT;
				List<Student> students = studentService.getAll();
				request.setAttribute("students", students);
        }

        request.getRequestDispatcher(forward).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding ("UTF-8");

		try {
			Student student = new Student();
			student.setFirstName(request.getParameter("firstName"));
			student.setLastName(request.getParameter("lastName"));
			student.setContactInformation(request.getParameter("contactInformation"));
			String[] option = request.getParameterValues("exampleSelect1");
			Group group;
			group = (Group) groupService.findById(Integer.parseInt(option[0]));
			student.setGroup(group);
			logger.info("Add new student in group number" + group.getGroupNumber());
			studentService.addNew(student);
		} catch (NumberFormatException e) {
			logger.error("Error when to add a new student", e);
		}
        RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
        request.setAttribute("students", studentService.getAll());
        view.forward(request, response);
	}
}


