package com.university.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.university.domain.entity.Group;
import com.university.exception.DaoException;
import com.university.domain.service.AbstaractService;

@WebServlet("/un")
public class UniversityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/un.jsp";
    private static String LIST_USER = "/addGroup.jsp";

    AbstaractService universityService = new AbstaractService(Group.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="startForward";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            try {
            	int id = Integer.parseInt(request.getParameter("id"));
            	universityService.delete(id);
                forward = INSERT_OR_EDIT;
				request.setAttribute("groups", universityService.getAll());
			} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			}
        } else if (action.equalsIgnoreCase("edit")){
        	try {
	            forward = LIST_USER;
	            int id = Integer.parseInt(request.getParameter("id"));
	            Group group = (Group) universityService.findById(id);
	            request.setAttribute("group", group);
        	} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			}
        } else if (action.equalsIgnoreCase("insert")){
        	try {
	            forward = LIST_USER;
	            request.setAttribute("groups", universityService.getAll());
        	} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
        	}
        } else {
        	 try {
            forward = INSERT_OR_EDIT;
				request.setAttribute("groups", universityService.getAll());
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

		Group group = new Group();
		try {
			group.setGroupNumber(request.getParameter("groupNumber"));
			universityService.addNew(group);
			RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
			request.setAttribute("groups", universityService.getAll());
			view.forward(request, response);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
