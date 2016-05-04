package com.university.dao;


import com.university.exception.DaoException;

/** Фабрика объектов для работы с базой данных */
public interface DaoFactory<T> {

    public interface DaoCreator<T> {
        public GenericDao create(T t);
    }

    /** Возвращает подключение к базе данных */
    public T getConnection() throws DaoException;

    /** Возвращает объект для управления персистентным состоянием объекта */
    public GenericDao getDao(T t, Class dtoClass) throws DaoException;
}
