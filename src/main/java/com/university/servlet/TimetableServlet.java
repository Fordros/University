package com.university.servlet;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.university.domain.entity.Group;
import com.university.domain.entity.Lecturer;
import com.university.domain.entity.Lesson;
import com.university.domain.service.GroupService;
import com.university.domain.service.LecturerService;
import com.university.domain.service.LessonService;

@Controller
public class TimetableServlet{
    final static Logger logger = Logger.getLogger(TimetableServlet.class);

    @Autowired
    LessonService lessonService;
    @Autowired
	GroupService groupService;
    @Autowired
	LecturerService lecturerService;

	@RequestMapping("timetable")
	public ModelAndView timetable(@RequestParam Integer searchFor) {
		//logger.info("Creating student. Data: " + student);
		ModelAndView mav = new ModelAndView("findTimetable");
		if(searchFor == 2){
			mav.addObject("groups", groupService.getAll());
			mav.addObject("searchFor", searchFor);
		}else {
			mav.addObject("lecturers", lecturerService.getAll());
			mav.addObject("searchFor", searchFor);
		}
		return mav;
	}

    @RequestMapping("findTimetable")
    public ModelAndView timetable(@ModelAttribute Lesson searchLesson,
								  @RequestParam(value = "optionTimetable") int optionTimetable,
								  @RequestParam(value = "typesearch") int typesearch) {
    	Group group = null;
		Lecturer lecturer = null;
		Date today = new Date();
		List<Lesson> lessons = lessonService.getAll();
		List<Lesson> result = new ArrayList<Lesson>();
		
        logger.info("Searching timetable for " + optionTimetable);
        if(typesearch == 2){
        	logger.info("Selecting timetable for the student");
			group = groupService.findById(optionTimetable);
			for ( Lesson l : lessons) {
		        if (l.getGroup().getGroupNumber().equals(group.getGroupNumber()) && l.getLessonTime().after(today) && l.getLessonTime().before(searchLesson.getLessonTime())){
		            result.add(l);
		        }
			}
        }else{
			logger.info("Selecting timetable for the lecturer");
			lecturer = lecturerService.findById(optionTimetable);
			for ( Lesson l : lessons) {
		        if (l.getLecturer().getId().equals(lecturer.getId()) && l.getLessonTime().after(today) && l.getLessonTime().before(searchLesson.getLessonTime())){
		            result.add(l);
		        }
		    }
        }
        return new ModelAndView("lessonList", "lessons", result);
    }
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}
