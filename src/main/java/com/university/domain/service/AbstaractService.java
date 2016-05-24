package com.university.domain.service;


import java.util.List;

import org.apache.log4j.Logger;

import com.university.dao.DaoFactory;
import com.university.dao.Identified;
import com.university.dao.impl.DaoFactoryImpl;
import com.university.exception.DaoException;
import com.university.exception.DomainException;

public class AbstaractService<T> {
	 final static Logger logger = Logger.getLogger(AbstaractService.class);
	 DaoFactory factory = new DaoFactoryImpl();
	 Class<T> dtoClass = null;

	 public AbstaractService(Class<T> dtoClass) {
	        this.dtoClass = dtoClass;
	    }

	public List<T> getAll(){
        List<T> entity;
		try {
			entity = factory.getDao(dtoClass).getAll();
		} catch (DaoException e) {
			logger.error("Error when to receive all rows" + dtoClass.getName(), e);
			throw new DomainException("Error by accessing the database layer",e);		
		}
        return entity;
   }


   public <dtoClass> Object findById(Integer id){
	   dtoClass entity;
	try {
		entity = (dtoClass) factory.getDao(dtoClass).getByPK(id);
	} catch (DaoException e) {
		logger.error("Error when trying to find by id" + dtoClass.getName(), e);
		throw new DomainException("Error by accessing the database layer",e);
	}
	   return entity;
   }


   public <dtoClass> void  addNew(T object){
	   try {
		factory.getDao(dtoClass).persist( (Identified) object);
	} catch (DaoException e) {
		logger.error("Error when to add a new row" + dtoClass.getName(), e);
		throw new DomainException("Error by accessing the database layer",e);
	}
    }


	public <dtoClass> void delete(Integer id){
		dtoClass entity;
		try {
			entity = (dtoClass) factory.getDao(dtoClass).getByPK(id);
			factory.getDao(dtoClass).delete((Identified) entity);
		} catch (DaoException e) {
			logger.error("Error when to try to remove" + dtoClass.getName(), e);
			throw new DomainException("Error when to try to remove",e);
		}
		
	}
}
