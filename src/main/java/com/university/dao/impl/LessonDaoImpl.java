package com.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.university.domain.entity.Lesson;
import com.university.exception.DaoException;
import com.university.util.HibernateUtil;
import org.springframework.stereotype.Repository;

@Repository
public class LessonDaoImpl extends GenericDAOImpl<Lesson, Integer> {
	@Autowired
    private HibernateUtil hibernateUtil;

	@Override
	public Lesson findByID(Integer id) throws DaoException {
		return hibernateUtil.fetchById(id,  Lesson.class);
	}
	
}
