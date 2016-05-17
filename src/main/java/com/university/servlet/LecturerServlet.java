package com.university.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.university.domain.Lecturer;
import com.university.exception.DaoException;
import com.university.service.AbstaractService;

@WebServlet("/lecturer")
public class LecturerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/lecturer.jsp";
    private static String LIST_USER = "/addLecturer.jsp";
    
    AbstaractService service = new AbstaractService(Lecturer.class);
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            try {
            	int id = Integer.parseInt(request.getParameter("id"));
            	service.delete(id);
                forward = INSERT_OR_EDIT;
				request.setAttribute("lecturers", service.getAll());
			} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			}
        } else if (action.equalsIgnoreCase("find")){
        	try {
	            forward = INSERT_OR_EDIT;
	            int id = Integer.parseInt(request.getParameter("id"));
	            Lecturer lecturer = (Lecturer) service.findById(id);
	            request.setAttribute("lecturer", lecturer);
        	} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			}
        } else if (action.equalsIgnoreCase("insert")){
        	try {
	            forward = LIST_USER;
	            request.setAttribute("lecturers", service.getAll());
        	} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
        	}
        } else {
        	 try {
            forward = INSERT_OR_EDIT;
				List<Lecturer> lecturers = service.getAll();
				request.setAttribute("lecturers", lecturers);
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

		Lecturer lecturer = new Lecturer();
		try {
			lecturer.setFirstName(request.getParameter("firstName"));
			lecturer.setLastName(request.getParameter("lastName"));
			lecturer.setContactInformation(request.getParameter("contactInformation"));
			lecturer.setQualification(request.getParameter("qualification"));
			service.addNew(lecturer);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
        RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
        try {
			request.setAttribute("lecturers", service.getAll());
		} catch (DaoException e) {
			e.printStackTrace();
		}
        view.forward(request, response);
	}
    
}
