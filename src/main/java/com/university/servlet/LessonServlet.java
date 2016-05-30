package com.university.servlet;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;





import com.university.domain.service.ClassroomService;
import com.university.domain.service.GroupService;
import com.university.domain.service.LecturerService;

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

import com.university.domain.entity.Lesson;
import com.university.domain.service.LessonService;



@Controller
public class LessonServlet{

    final static Logger logger = Logger.getLogger(LessonServlet.class);

    @Autowired
	private LessonService lessonService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private LecturerService lecturerService;
	@Autowired
	private ClassroomService classroomService;

	@RequestMapping("addLesson")
	public ModelAndView addLesson(@ModelAttribute Lesson lesson) {
		logger.info("Creating lesson. Data: " + lesson);
		ModelAndView mav = new ModelAndView("lessonForm");

		mav.addObject("groups",groupService.getAll());
		mav.addObject("lecturers",lecturerService.getAll());
		mav.addObject("classrooms",classroomService.getAll());

		return mav;
	}

	@RequestMapping("editLesson")
	public ModelAndView editLesson(@RequestParam Integer id,
			@ModelAttribute Lesson lesson) {
		logger.info("Updating the lesson for the Id " + id);
		ModelAndView mav = new ModelAndView("lessonForm");

		mav.addObject("groups",groupService.getAll());
		mav.addObject("lecturers",lecturerService.getAll());
		mav.addObject("classrooms",classroomService.getAll());
		lesson  = lessonService.findById(id);
        mav.addObject("lesson", lesson);
		return mav;
	}

	@RequestMapping("saveLesson")
	public ModelAndView saveLesson(@ModelAttribute Lesson lesson,
                                   @RequestParam(value = "optionGroup") int optionGroup,
                                   @RequestParam(value = "optionLecturer") int optionLecturer,
                                   @RequestParam(value = "optionClassroom") int optionClassroom,
                                   @RequestParam(value = "studiesType") String studiesType
                                   ) {
		logger.info("Saving the lesson. Data : " + lesson);
		if (lesson.getId() == null) {
			lesson.setGroup(groupService.findById(optionGroup));
			lesson.setClassroom(classroomService.findById(optionClassroom));
			lesson.setLecturer(lecturerService.findById(optionLecturer));
			lesson.setStudiesTypes(studiesType);

            lessonService.addNew(lesson);
		} else {
			lesson.setGroup(groupService.findById(optionGroup));
			lesson.setClassroom(classroomService.findById(optionClassroom));
			lesson.setLecturer(lecturerService.findById(optionLecturer));
			lesson.setStudiesTypes(studiesType);

			lessonService.update(lesson);
		}
		return new ModelAndView("redirect:getAllLessons");
	}

	@RequestMapping("deleteLesson")
    public ModelAndView deleteLesson(@RequestParam Integer id) {
        logger.info("Deleting the lesson. Id : "+id);
        Lesson lesson = lessonService.findById(id);
        lessonService.delete(lesson);
        return new ModelAndView("redirect:getAllLessons");
    }

    @RequestMapping(value = {"getAllLessons", "/"})
    public ModelAndView getAllLessons() {
        logger.info("Getting the all lessons.");
        List<Lesson> lessons = lessonService.getAll();
        return new ModelAndView("lessonList", "lessons", lessons);
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
