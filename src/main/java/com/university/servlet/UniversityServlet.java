package com.university.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.domain.entity.Group;
import com.university.domain.service.AbstaractService;

@WebServlet("/un")
public class UniversityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/un.jsp";
    private static String LIST_USER = "/addGroup.jsp";
    final static Logger logger = Logger.getLogger(UniversityServlet.class);

    AbstaractService universityService = new AbstaractService(Group.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="startForward";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Delete group by id" + id);
			universityService.delete(id);
			forward = INSERT_OR_EDIT;
			request.setAttribute("groups", universityService.getAll());
        } else if (action.equalsIgnoreCase("find")){
        	forward = LIST_USER;
			int id = Integer.parseInt(request.getParameter("id"));
			logger.info("Find group by id" + id);
			Group group = (Group) universityService.findById(id);
			request.setAttribute("group", group);
        } else if (action.equalsIgnoreCase("insert")){
        	forward = LIST_USER;
			request.setAttribute("groups", universityService.getAll());
        } else {
        	 forward = INSERT_OR_EDIT;
				request.setAttribute("groups", universityService.getAll());
        }

        request.getRequestDispatcher(forward).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding ("UTF-8");

		Group group = new Group();
		group.setGroupNumber(request.getParameter("groupNumber"));
		logger.info("Add new group");
		universityService.addNew(group);
		RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
		request.setAttribute("groups", universityService.getAll());
		view.forward(request, response);
	}
}
