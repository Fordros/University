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

import org.apache.log4j.Logger;

import com.university.domain.entity.Classroom;
import com.university.domain.entity.Group;
import com.university.domain.entity.Lecturer;
import com.university.domain.entity.Lesson;
import com.university.domain.entity.Student;
import com.university.domain.service.AbstaractService;

@WebServlet("/lesson")
public class LessonServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/lesson.jsp";
    private static String LIST_USER = "/addLesson.jsp";
    final static Logger logger = Logger.getLogger(LessonServlet.class);
    
    AbstaractService lessonService = new AbstaractService(Lesson.class);
    AbstaractService studentService = new AbstaractService(Student.class);
	AbstaractService groupService = new AbstaractService(Group.class);
	AbstaractService classroomService = new AbstaractService(Classroom.class);
	AbstaractService lecturerService = new AbstaractService(Lecturer.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Delete lesson by id" + id);
			lessonService.delete(id);
			forward = INSERT_OR_EDIT;
			request.setAttribute("lessons", lessonService.getAll());
        } else if (action.equalsIgnoreCase("find")){
        	forward = "timetable.jsp";
			int candidate = Integer.parseInt(request.getParameter("for"));
			if(candidate == 1){
				request.setAttribute("lecturers", lecturerService.getAll());
				request.setAttribute("name", candidate);
			}else{
				request.setAttribute("groups", groupService.getAll());
				request.setAttribute("name", candidate);
			}
        } else if (action.equalsIgnoreCase("insert")){
        	forward = LIST_USER;
			request.setAttribute("groups", groupService.getAll());
			request.setAttribute("lecturers", lecturerService.getAll());
			request.setAttribute("classrooms", classroomService.getAll());
        } else {
        	 forward = INSERT_OR_EDIT;
			List<Lesson> lessons = lessonService.getAll();
			request.setAttribute("lessons", lessons);
        }
        request.getRequestDispatcher(forward).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding ("UTF-8");
		logger.info("Add new lesson");
		Lesson lesson = new Lesson();
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
			logger.error("Error to parse lessontime", e);
		}
		lessonService.addNew(lesson);
		List<Lesson> lessons = lessonService.getAll();
		request.setAttribute("lessons", lessons);
        request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
	}
}
