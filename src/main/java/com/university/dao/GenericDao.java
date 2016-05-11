package com.university.dao;

import com.university.exception.DaoException;

import java.io.Serializable;
import java.util.List;

/**
 * The unified state of persistent object management interface
 */
public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {


    public T create() throws DaoException;

    public T persist(T object)  throws DaoException;

    public T getByPK(PK key) throws DaoException;

    public void update(T object) throws DaoException;

    public void delete(T object) throws DaoException;

    public List<T> getAll() throws DaoException;
}
