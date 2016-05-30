package com.university.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.university.domain.entity.Classroom;
import com.university.exception.DaoException;
import com.university.util.HibernateUtil;
import org.springframework.stereotype.Repository;


@Repository
public class ClassroomDaoImpl extends GenericDAOImpl<Classroom, Integer> {
    
	@Autowired
    private HibernateUtil hibernateUtil;
	
	public Classroom findByClassroomNumber(String classroomNumber) {
		Classroom classroom = null;
        String sql = "SELECT a FROM Classroom a WHERE a.number = " + classroomNumber;
        classroom = (Classroom) hibernateUtil.fetchAll(sql);
        return classroom;
    }

	@Override
	public Classroom findByID(Integer id) throws DaoException {
		return hibernateUtil.fetchById(id,  Classroom.class);
	}
  
}
