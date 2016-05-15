package com.university.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.university.dao.DaoFactory;
import com.university.dao.Identified;
import com.university.dao.impl.DaoFactoryImpl;
import com.university.domain.Group;
import com.university.domain.Student;
import com.university.exception.DaoException;

public class AbstaractService<T> {

	 DaoFactory factory = new DaoFactoryImpl();
	 Class<T> dtoClass = null;

	 public AbstaractService(Class<T> dtoClass) {
	        this.dtoClass = dtoClass;
	    }

	public List<T> getAll() throws DaoException{
        List<T> entity = factory.getDao(dtoClass).getAll();
        return entity;
   }


   public <dtoClass> Object findById(Integer id) throws DaoException{

	   dtoClass entity =  (dtoClass) factory.getDao(dtoClass).getByPK(id);
	   return entity;
   }


   public <dtoClass> void  addNew(T object) throws DaoException{
	   dtoClass entity = (dtoClass) factory.getDao(dtoClass).create();
	   factory.getDao(dtoClass).persist( (Identified) object);
    }


	public <dtoClass> void delete(Integer id) throws DaoException {
		dtoClass entity =  (dtoClass) factory.getDao(dtoClass).getByPK(id);
		factory.getDao(dtoClass).delete((Identified) entity);
	}
}
