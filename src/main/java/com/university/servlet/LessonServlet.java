package com.university.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@WebServlet("/lesson")
public class LessonServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/lesson.jsp";
    private static String LIST_USER = "/addLesson.jsp";

    AbstaractService lessonService = new AbstaractService(Lesson.class);
    AbstaractService studentService = new AbstaractService(Student.class);
	AbstaractService groupService = new AbstaractService(Group.class);
	AbstaractService classroomService = new AbstaractService(Classroom.class);
	AbstaractService lecturerService = new AbstaractService(Lecturer.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            try {
            	int id = Integer.parseInt(request.getParameter("id"));
            	lessonService.delete(id);
                forward = INSERT_OR_EDIT;
				request.setAttribute("lessons", lessonService.getAll());
			} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			}
        } else if (action.equalsIgnoreCase("find")){
        	try {
	            forward = "timetable.jsp";
	            int candidate = Integer.parseInt(request.getParameter("for"));
	            if(candidate == 1){
	            	request.setAttribute("lecturers", lecturerService.getAll());
	            	request.setAttribute("name", candidate);
	            }else{
	            	request.setAttribute("groups", groupService.getAll());
	            	request.setAttribute("name", candidate);
	            }
        	} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			}
        } else if (action.equalsIgnoreCase("insert")){
        	try {
	            forward = LIST_USER;
	            request.setAttribute("groups", groupService.getAll());
	            request.setAttribute("lecturers", lecturerService.getAll());
	            request.setAttribute("classrooms", classroomService.getAll());
        	} catch (DaoException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
        	}
        } else {
        	 try {
	            forward = INSERT_OR_EDIT;
	            List<Lesson> lessons = lessonService.getAll();
				request.setAttribute("lessons", lessons);
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
		Lesson lesson = new Lesson();
		try {
			String[] optionGroup = request.getParameterValues("optionGroup");
			Group group = (Group) groupService.findById(Integer.parseInt(optionGroup[0]));

			String[] optionLecturer = request.getParameterValues("optionLecturer");
			Lecturer lecturer = (Lecturer) lecturerService.findById(Integer.parseInt(optionLecturer[0]));

			String[] optionClassroom = request.getParameterValues("optionClassroom");
			Classroom classroom = (Classroom) classroomService.findById(Integer.parseInt(optionClassroom[0]));

			lesson.setGroup(group);
			lesson.setLecturer(lecturer);
			lesson.setClassroom(classroom);
			lesson.setStudiesTypes(request.getParameter("studiesType"));

			try {
				String lessonTimeString = request.getParameter("lessonTime");
	            Date lessonTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(lessonTimeString);

	            lesson.setLessonTime(lessonTime);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			lessonService.addNew(lesson);

		} catch (DaoException e) {
			e.printStackTrace();
		}
		try {
			List<Lesson> lessons = lessonService.getAll();
			request.setAttribute("lessons", lessons);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
	}
}
