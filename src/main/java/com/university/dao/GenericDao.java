package com.university.dao;


import com.university.exception.DaoException;

import java.io.Serializable;
import java.util.List;


/**
 * The unified state of persistent object management interface
 */
public interface GenericDao<T, ID extends Serializable> {

	public Integer save(T entity) throws DaoException;

    public T merge(T entity) throws DaoException;

    public void delete(T entity) throws DaoException;

    public List findAll(Class clazz) throws DaoException;

    public T findByID(Integer id) throws DaoException;

}
