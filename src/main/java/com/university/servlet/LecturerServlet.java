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

import com.university.domain.entity.Lecturer;
import com.university.domain.service.AbstaractService;

@WebServlet("/lecturer")
public class LecturerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/lecturer.jsp";
    private static String LIST_USER = "/addLecturer.jsp";
    final static Logger logger = Logger.getLogger(GroupServlet.class);
    AbstaractService service = new AbstaractService(LecturerServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Delete lecturer by id" + id);
			service.delete(id);
			forward = INSERT_OR_EDIT;
			request.setAttribute("lecturers", service.getAll());
        } else if (action.equalsIgnoreCase("find")){
        	forward = INSERT_OR_EDIT;
			int id = Integer.parseInt(request.getParameter("id"));
			logger.info("Find lecturer by id" + id);
			Lecturer lecturer = (Lecturer) service.findById(id);
			request.setAttribute("lecturer", lecturer);
        } else if (action.equalsIgnoreCase("insert")){
        	forward = LIST_USER;
			request.setAttribute("lecturers", service.getAll());
        } else {
        	 forward = INSERT_OR_EDIT;
				List<Lecturer> lecturers = service.getAll();
				request.setAttribute("lecturers", lecturers);
        }
        request.getRequestDispatcher(forward).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding ("UTF-8");
		logger.info("Add new lecturer");
		Lecturer lecturer = new Lecturer();
		lecturer.setFirstName(request.getParameter("firstName"));
		lecturer.setLastName(request.getParameter("lastName"));
		lecturer.setContactInformation(request.getParameter("contactInformation"));
		lecturer.setQualification(request.getParameter("qualification"));
		service.addNew(lecturer);

        RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
        request.setAttribute("lecturers", service.getAll());
        view.forward(request, response);
	}

}
