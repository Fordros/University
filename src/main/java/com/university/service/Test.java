package com.university.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.university.dao.DaoFactory;
import com.university.dao.impl.DaoFactoryImpl;
import com.university.domain.Group;
import com.university.domain.Student;
import com.university.exception.DaoException;

public class Test {

	public static void main(String[] args) throws DaoException {
		AbstaractService universityService = new AbstaractService(Group.class);
		 List<Group> groups =  universityService.getAll();
		Group group = (Group) universityService.findById(1);
		Group group1 = new Group();

		group1.setGroupNumber("testService");
		universityService.addNew(group1);
		 System.out.println(groups);
	}

}
