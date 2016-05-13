package com.university.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.university.exception.DaoException;
import com.university.exception.DomainException;


public abstract class BaseServlet {
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	private final static String ERROR_PAGE_URL = "error.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DaoException{
		this.request = request;
		this.response = response;

		try {
			String[] urlParts = request.getRequestURI().split("/");
			String method = urlParts[3];

			switch (method) {
			case "list":
				viewAll();
				break;
			case "prepare":
				prepareCreating();
				break;
			case "create":
				create();
				break;
			case "delete":
				delete(Integer.parseInt(urlParts[4]));
				break;
			case "edit":
				edit(Integer.parseInt(urlParts[4]));
				break;
			case "save":
				save();
				break;
			default:
				request.getRequestDispatcher(ERROR_PAGE_URL).forward(request, response);
			}
		} catch (DomainException | IOException | ServletException e) {
			request.getRequestDispatcher(ERROR_PAGE_URL).forward(request, response);
		}
	}

	protected abstract void save() throws ServletException, IOException;

	protected abstract void edit(Integer parseInt) throws ServletException, IOException;

	protected abstract void delete(Integer parseInt) throws ServletException, IOException;

	protected abstract void create() throws ServletException, IOException;

	protected void prepareCreating() throws ServletException, IOException {

	};

	protected abstract void viewAll() throws ServletException, IOException, DaoException;
}
