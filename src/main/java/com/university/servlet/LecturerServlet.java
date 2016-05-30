package com.university.servlet;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.university.domain.entity.Lecturer;
import com.university.domain.service.LecturerService;


@Controller
public class LecturerServlet{

    final static Logger logger = Logger.getLogger(GroupServlet.class);

    @Autowired
	private LecturerService lecturerService;

	@RequestMapping("addLecturer")
	public ModelAndView addLecturer(@ModelAttribute Lecturer lecturer) {
		logger.info("Creating lecturer. Data: " + lecturer);
		return new ModelAndView("lecturerForm");
	}

	@RequestMapping("editLecturer")
	public ModelAndView editLecturer(@RequestParam Integer id,
			@ModelAttribute Lecturer lecturer) {
		logger.info("Updating the lecturer for the Id " + id);
		lecturer  = lecturerService.findById(id);
		return new ModelAndView("lecturerForm", "lecturer", lecturer);
	}

	@RequestMapping("saveLecturer")
	public ModelAndView Lecturer(@ModelAttribute Lecturer lecturer,
			@RequestParam(value = "qualification") String qualification) {
		logger.info("Saving the lecturer. Data : " + lecturer);
		if (lecturer.getId() == null) {
			lecturer.setQualification(qualification);
			lecturerService.addNew(lecturer);
		} else {
			lecturer.setQualification(qualification);
			lecturerService.update(lecturer);
		}
		return new ModelAndView("redirect:getAllLecturers");
	}

	@RequestMapping("deleteLecturer")
    public ModelAndView deleteLecturer(@RequestParam Integer id) {
        logger.info("Deleting the lecturer. Id : "+id);
        Lecturer lecturer = lecturerService.findById(id);
        lecturerService.delete(lecturer);
        return new ModelAndView("redirect:getAllLecturers");
    }

    @RequestMapping(value = {"getAllLecturers", "/"})
    public ModelAndView getAllLecturers() {
        logger.info("Getting the all lecturers.");
        List<Lecturer> lecturers = lecturerService.getAll();
        return new ModelAndView("lecturerList", "lecturers", lecturers);
    }

    @RequestMapping("searchLecturer")
    public ModelAndView searchLecturer(@RequestParam("searchName") String lastName) {
        logger.info("Searching the lecturer. Lecturer last name: "+lastName);
        List<Lecturer> lecturers = lecturerService.findLecturerByLastName(lastName);
        return new ModelAndView("lecturerList", "lecturers", lecturers);
    }

}
