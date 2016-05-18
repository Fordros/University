package com.university.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.university.domain.Classroom;
import com.university.domain.Group;
import com.university.domain.Lecturer;
import com.university.domain.Lesson;
import com.university.domain.Student;
import com.university.exception.DaoException;
import com.university.service.AbstaractService;

@WebServlet("/timetable")
public class TimetableServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private static String LIST_USER = "/lesson.jsp";

    AbstaractService lessonService = new AbstaractService(Lesson.class);
    AbstaractService studentService = new AbstaractService(Student.class);
	AbstaractService groupService = new AbstaractService(Group.class);
	AbstaractService classroomService = new AbstaractService(Classroom.class);
	AbstaractService lecturerService = new AbstaractService(Lecturer.class);


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding ("UTF-8");
		Group group = null;
		Lecturer lecturer = null;
		Date today = new Date();
		try {
			List<Lesson> lessons = lessonService.getAll();
			List<Lesson> result = new ArrayList<Lesson>();
			String lessonTimeString = request.getParameter("lessonTime");
            Date lessonTime = null;

			try {
				lessonTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(lessonTimeString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String[] optionTimetable = request.getParameterValues("optionTimetable");
			int typesearch = Integer.parseInt(request.getParameter("typesearch"));
			if(typesearch == 2){
				group = (Group) groupService.findById(Integer.parseInt(optionTimetable[0]));
				for ( Lesson l : lessons) {
		            if (l.getGroup().getGroupNumber().equals(group.getGroupNumber()) && l.getLessonTime().after(today) && l.getLessonTime().before(lessonTime)){
		                result.add(l);
		            }
		        }
			}else{
				lecturer = (Lecturer) lecturerService.findById(Integer.parseInt(optionTimetable[0]));
				for ( Lesson l : lessons) {
		            if (l.getLecturer().getId().equals(lecturer.getId()) && l.getLessonTime().after(today) && l.getLessonTime().before(lessonTime)){
		                result.add(l);
		            }
		        }
			}
	        request.setAttribute("lessons", result);
		} catch (DaoException e) {
			e.printStackTrace();
		}

        request.getRequestDispatcher(LIST_USER).forward(request, response);
	}

}
