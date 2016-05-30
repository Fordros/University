package com.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.university.domain.entity.Student;
import com.university.exception.DaoException;
import com.university.util.HibernateUtil;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl extends GenericDAOImpl<Student, Integer> {

	@Autowired
    private HibernateUtil hibernateUtil;


	@Override
	public Student findByID(Integer id) throws DaoException {
		return hibernateUtil.fetchById(id,  Student.class);
	}

	public List<Student> getAllStudentByLastName(String studentLastName) {
		String query = "SELECT e.* FROM student e WHERE e.name like '%"+ studentLastName +"%'";
		List<Student> students = hibernateUtil.fetchAll(query);
		return students;
	}


}
