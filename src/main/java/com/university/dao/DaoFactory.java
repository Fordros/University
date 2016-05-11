package com.university.dao;


import com.university.exception.DaoException;

public interface DaoFactory<T> {

    public interface DaoCreator<T> {
        public GenericDao create(T t);
    }

    public T getConnection() throws DaoException;

    /** Gets an object for managing persistent state of the object */
    public GenericDao getDao(Class dtoClass) throws DaoException;
}
