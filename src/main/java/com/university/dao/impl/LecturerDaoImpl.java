package com.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.university.domain.entity.Lecturer;
import com.university.exception.DaoException;
import com.university.util.HibernateUtil;

import org.springframework.stereotype.Repository;


@Repository
public class LecturerDaoImpl extends GenericDAOImpl<Lecturer, Integer> {

	@Autowired
    private HibernateUtil hibernateUtil;


	@Override
	public Lecturer findByID(Integer id) throws DaoException {
		return hibernateUtil.fetchById(id,  Lecturer.class);
	}


	public List<Lecturer> getAllLecturerByLastName(String lecturerLastName) {
		String query = "SELECT e.* FROM lecturer e WHERE e.name like '%"+ lecturerLastName +"%'";
		List<Lecturer> lecturers = hibernateUtil.fetchAll(query);
		return lecturers;
	}


}
