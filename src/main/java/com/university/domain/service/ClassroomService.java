package com.university.domain.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.university.dao.impl.ClassroomDaoImpl;
import com.university.domain.entity.Classroom;
import com.university.exception.DomainException;

@Service
@Transactional
public class ClassroomService {
	final static Logger logger = Logger.getLogger(ClassroomService.class);

	@Autowired
	private ClassroomDaoImpl classroomDao;

	public List<Classroom> getAll() {
		try {
			return classroomDao.findAll(Classroom.class);
		} catch (HibernateException e) {
			logger.error("Error when trying to find all classrooms", e);
			throw new DomainException(
					"Error when trying to find all classrooms", e);
		}
	}

	public Classroom findById(Integer id) {
		try {
			return classroomDao.findByID(Classroom.class, id);
		} catch (HibernateException e) {
			logger.error("Error when trying to find classroom by id", e);
			throw new DomainException(
					"Error when trying to find classroom by id", e);
		}
	}

	public void addNew(Classroom classroom) {
		try {
			classroomDao.save(classroom);
		} catch (HibernateException e) {
			logger.error("Error when to add a new classroom", e);
			throw new DomainException("Error when to add a new classroom", e);
		}
	}

	public void delete(Classroom classroom) {
		try {
			classroomDao.delete(classroom);
		} catch (HibernateException e) {
			logger.error("Error when to try to remove classroom", e);
			throw new DomainException("Error when to try to remove classroom",
					e);
		}

	}

	public void update(Classroom classroom) {
		try {
			classroomDao.merge(classroom);
		} catch (HibernateException e) {
			logger.error("Error when to try to update classroom", e);
			throw new DomainException("Error when to try to update classroom",
					e);
		}

	}

}
