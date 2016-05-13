package com.university.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.university.dao.DaoFactory;
import com.university.dao.impl.DaoFactoryImpl;
import com.university.domain.Student;
import com.university.exception.DaoException;

public class TestStudent {

	public static void main(String[] args) throws DaoException {
		 DaoFactory factory = new DaoFactoryImpl();
		 List<Student> students =  factory.getDao(Student.class).getAll();
		
		 System.out.println(students);
	}

}
