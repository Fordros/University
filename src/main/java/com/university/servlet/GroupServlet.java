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
import com.university.domain.service.GroupService;

@Controller
public class GroupServlet {

	final static Logger logger = Logger.getLogger(GroupServlet.class);

	@Autowired
	private GroupService groupService;

	@RequestMapping("addGroup")
	public ModelAndView addGroup(@ModelAttribute Group group) {
		logger.info("Creating group. Data: " + group);
		return new ModelAndView("groupForm");
	}

	@RequestMapping("editGroup")
	public ModelAndView editGroup(@RequestParam Integer id,
			@ModelAttribute Group group) {
		logger.info("Updating the Group for the Id " + id);
		group  = groupService.findById(id);
		return new ModelAndView("groupForm", "group", group);
	}

	@RequestMapping("saveGroup")
	public ModelAndView saveGroup(@ModelAttribute Group group) {
		logger.info("Saving the group. Data : " + group);
		if (group.getId() == null) { // if group id is 0 then creating the
									// group other updating the group
			groupService.addNew(group);
		} else {
			groupService.update(group);
		}
		return new ModelAndView("redirect:getAllGroups");
	}

	@RequestMapping("deleteGroup")
    public ModelAndView deleteGroup(@RequestParam Integer id) {
        logger.info("Deleting the group. Id : "+id);
        Group group = groupService.findById(id);
        groupService.delete(group);
        return new ModelAndView("redirect:getAllGroups");
    }

    @RequestMapping(value = {"getAllGroups", "/"})
    public ModelAndView getAllGroups() {
        logger.info("Getting the all groups.");
        List<Group> groups = groupService.getAll();
        return new ModelAndView("groupList", "groups", groups);
    }

    @RequestMapping("searchGroup")
    public ModelAndView searchGroup(@RequestParam("searchName") String groupNumber) {
        logger.info("Searching the group. Group Names: "+groupNumber);
        Group group = groupService.findByGroupNumber(groupNumber);
        return new ModelAndView("groupList", "group", group);
    }


}
