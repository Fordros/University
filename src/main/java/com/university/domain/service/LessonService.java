package com.university.domain.service;


import com.university.dao.impl.LessonDaoImpl;
import com.university.domain.entity.Lesson;
import com.university.exception.DaoException;
import com.university.exception.DomainException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LessonService {
	 final static Logger logger = Logger.getLogger(LessonService.class);

	@Autowired
	private LessonDaoImpl lessonDao;


	public List<Lesson> getAll(){
		try {
			return lessonDao.findAll(Lesson.class);
		} catch (HibernateException e) {
			logger.error("Error when trying to find all lessons", e);
			throw new DomainException("Error when trying to find all lessons",e);
		}

	}

	public Lesson findById(Integer id) {
		try {
			return lessonDao.findByID(id);
		} catch (DaoException e) {
			logger.error("Error when trying to find lesson by id", e);
			throw new DomainException("Error when trying to find lesson by id",e);
		}
	}


	   public void addNew(Lesson lesson){
		   try {
			   lessonDao.save(lesson);
		} catch (HibernateException e) {
			logger.error("Error when to add a new lesson", e);
			throw new DomainException("Error when to add a new lesson",e);
		}
    }


	public void delete(Lesson lesson){
		try {
			lessonDao.delete(lesson);
		} catch (HibernateException e) {
			logger.error("Error when to try to remove lesson", e);
			throw new DomainException("Error when to try to remove lesson",e);
		}

	}

	public void update(Lesson lesson){
		try {
			lessonDao.merge(lesson);
		} catch (HibernateException e) {
			logger.error("Error when to try to update lesson", e);
			throw new DomainException("Error when to try to update lesson",e);
		}

	}
}
