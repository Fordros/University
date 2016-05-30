package com.university.servlet;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.university.domain.entity.Group;
import com.university.domain.entity.Student;
import com.university.domain.service.GroupService;
import com.university.domain.service.StudentService;

@Controller
public class StudentServlet {

    final static Logger logger = Logger.getLogger(StudentServlet.class);

    @Autowired
	private StudentService studentService;
    @Autowired
    private GroupService groupService;



	@RequestMapping("addStudent")
	public ModelAndView addStudent(@ModelAttribute Student student) {
		logger.info("Creating student. Data: " + student);
		List<Group> groups = groupService.getAll();
		return new ModelAndView("studentForm", "groups", groups);
	}

	@RequestMapping("editStudent")
	public ModelAndView editStudent(@RequestParam Integer id,
			@ModelAttribute Student student) {
		logger.info("Updating the student for the Id " + id);
		ModelAndView mav = new ModelAndView("studentForm");
		student  = studentService.findById(id);
		List<Group> groups;
		groups = groupService.getAll();
		mav.addObject("student", student);
		mav.addObject("groups", groups);

		return mav;
	}

	@RequestMapping("saveStudent")
	public ModelAndView Student(@ModelAttribute Student student,
			@RequestParam(value = "groupSelect") int groupSelect) {
		logger.info("Saving the student. Data : " + student);
		if (student.getId() == null) {
			Group group = groupService.findById(groupSelect);
			student.setGroup(group);
			studentService.addNew(student);
		} else {
			Group group = groupService.findById(groupSelect);
			student.setGroup(group);
			studentService.update(student);
		}
		return new ModelAndView("redirect:getAllStudents");
	}

	@RequestMapping("deleteStudent")
    public ModelAndView deleteStudent(@RequestParam Integer id) {
        logger.info("Deleting the student. Id : "+id);
        Student student = studentService.findById(id);
        studentService.delete(student);
        return new ModelAndView("redirect:getAllStudents");
    }

    @RequestMapping(value = {"getAllStudents", "/"})
    public ModelAndView getAllStudents() {
        logger.info("Getting the all students.");
        List<Student> students = studentService.getAll();
        return new ModelAndView("studentList", "students", students);
    }

    @RequestMapping("searchStudent")
    public ModelAndView searchStudent(@RequestParam("searchName") String lastName) {
        logger.info("Searching the student. Student last name: "+lastName);
        List<Student> students = studentService.findStudentByLastName(lastName);
        return new ModelAndView("studentList", "students", students);
    }



}
