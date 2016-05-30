package com.university.domain.service;

import com.university.dao.impl.StudentDaoImpl;
import com.university.domain.entity.Student;
import com.university.exception.DomainException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {
	final static Logger logger = Logger.getLogger(StudentService.class);

	@Autowired
	private StudentDaoImpl studentDAO;

	public Integer addNew(Student student) {
		try {
			return studentDAO.save(student);
		} catch (HibernateException e) {
			logger.error("Error when to add a new student", e);
			throw new DomainException("Error when to add a new student",
					e);
		}
	}
	
	public List<Student> findStudentByLastName(String lastName) {
		try {
			return studentDAO.getAllStudentByLastName(lastName);
		} catch (HibernateException e) {
			logger.error("Error when trying to find all students by last name", e);
			throw new DomainException("Error when trying to find all students by last name",
					e);
		}
	}

	public List<Student> getAll() {
		List<Student> students;
		try {
			students = studentDAO.findAll(Student.class);
		} catch (HibernateException e) {
			logger.error("Error when trying to find all students", e);
			throw new DomainException("Error when trying to find all students",
					e);
		}
		return students;
	}

	public Student findById(Integer id) {
		try {
			return studentDAO.findByID(Student.class, id);
		} catch (HibernateException e) {
			logger.error("Error when trying to find student by id", e);
			throw new DomainException(
					"Error when trying to find student by id", e);
		}
	}

	public void delete(Student student) {
		try {
			studentDAO.delete(student);
		} catch (HibernateException e) {
			logger.error("Error when to try to remove student", e);
			throw new DomainException("Error when to try to remove student", e);
		}

	}

	public void update(Student student) {
		try {
			studentDAO.merge(student);
		} catch (HibernateException e) {
			logger.error("Error when to try to update student", e);
			throw new DomainException("Error when to try to update student", e);
		}

	}
}
