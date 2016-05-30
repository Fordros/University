package com.university.domain.service;

import com.university.dao.impl.LecturerDaoImpl;
import com.university.domain.entity.Lecturer;
import com.university.exception.DomainException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LecturerService {
	final static Logger logger = Logger.getLogger(LecturerService.class);

	@Autowired
	private LecturerDaoImpl lecturerDao;

	public List<Lecturer> getAll() {

		try {
			return lecturerDao.findAll(Lecturer.class);
		} catch (HibernateException e) {
			logger.error("Error when trying to find all lecturers", e);
			throw new DomainException(
					"Error when trying to find all lecturers", e);
		}
	}
	
	public List<Lecturer> findLecturerByLastName(String lastName) {
		try {
			return lecturerDao.getAllLecturerByLastName(lastName);
		} catch (HibernateException e) {
			logger.error("Error when trying to find all lecturers by last name", e);
			throw new DomainException("Error when trying to find all lecturers by last name",
					e);
		}
	}

	public Lecturer findById(Integer id) {
		try {
			return lecturerDao.findByID(Lecturer.class, id);
		} catch (HibernateException e) {
			logger.error("Error when trying to find lecturer by id", e);
			throw new DomainException(
					"Error when trying to find lecturer by id", e);
		}
	}

	public void addNew(Lecturer lecturer) {
		try {
			lecturerDao.save(lecturer);
		} catch (HibernateException e) {
			logger.error("Error when to add a new lecturer", e);
			throw new DomainException("Error when to add a new lecturer", e);
		}
	}

	public void delete(Lecturer lecturer) {
		try {
			lecturerDao.delete(lecturer);
		} catch (HibernateException e) {
			logger.error("Error when to try to remove lecturer", e);
			throw new DomainException("Error when to try to remove lecturer", e);
		}

	}

	public void update(Lecturer lecturer) {
		try {
			lecturerDao.merge(lecturer);
		} catch (HibernateException e) {
			logger.error("Error when to try to update lecturer", e);
			throw new DomainException("Error when to try to update lecturer", e);
		}

	}
}
