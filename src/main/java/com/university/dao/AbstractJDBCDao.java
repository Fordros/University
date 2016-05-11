package com.university.dao;

import com.university.exception.DaoException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class AbstractJDBCDao<T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {


    public abstract String getSelectQuery();
    public abstract String getCreateQuery();
    public abstract String getUpdateQuery();
    public abstract String getDeleteQuery();
    protected abstract List<T> parseResultSet(ResultSet rs) throws DaoException;
    
    /**
     * Sets insert query arguments in accordance with field value object
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DaoException;
    
    /**
     * Sets update query arguments in accordance with field value object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws DaoException;

    private DaoFactory<Connection> parentFactory;
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet rs = null;


    private Set<ManyToOne> relations = new HashSet<ManyToOne>();

    @Override
    public T getByPK(Integer key) throws DaoException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try {
        	statement = connection.prepareStatement(sql);
            statement.setInt(1, key);
            rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DaoException(e);
        }finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Error occured while closing connection, statement, resultSet", e);
			}
		}
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new DaoException("Received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> list;
        String sql = getSelectQuery();
        try {
        	statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DaoException(e);
        }finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Error occured while closing connection, statement, resultSet", e);
			}
		}
        return list;
    }

    @Override
    public T persist(T object) throws DaoException {
        if (object.getId() != null) {
            throw new DaoException("Object is already persist.");
        }
        saveDependence(object);

        T persistInstance;

        String sql = getCreateQuery();
        try {
        	statement = connection.prepareStatement(sql);
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        // Get the newly inserted record
        sql = getSelectQuery() + " ORDER BY id DESC LIMIT 1";
        try {
        	statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new DaoException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new DaoException(e);
        }finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Error occured while closing connection, statement, resultSet", e);
			}
		}
        return persistInstance;
    }

    @Override
    public void update(T object) throws DaoException {
        saveDependence(object);
        String sql = getUpdateQuery();
        try {
        	statement = connection.prepareStatement(sql);
            prepareStatementForUpdate(statement, object); // заполнение аргументов запроса оставляем на совесть потомков
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Error occured while closing connection, statement, resultSet", e);
			}
		}
    }

    @Override
    public void delete(T object) throws DaoException {
        String sql = getDeleteQuery();
        try {
        	statement = connection.prepareStatement(sql);
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new DaoException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Error occured while closing connection, statement, resultSet", e);
			}
		}
    }

    public AbstractJDBCDao(DaoFactory<Connection> parentFactory, Connection connection) {
        this.parentFactory = parentFactory;
        this.connection = connection;
    }

    protected Identified getDependence(Class<? extends Identified> dtoClass, Serializable pk) throws DaoException {
        return parentFactory.getDao(dtoClass).getByPK(pk);
    }

    protected boolean addRelation(Class<? extends Identified> ownerClass, String field) {
        try {
            return relations.add(new ManyToOne(ownerClass, parentFactory, field));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveDependence(Identified owner) throws DaoException {
        for (ManyToOne m : relations) {
            try {
                if (m.getDependence(owner) == null) {
                    continue;
                }
                if (m.getDependence(owner).getId() == null) {
                    Identified depend = m.persistDependence(owner, connection);
                    m.setDependence(owner, depend);
                } else {
                    m.updateDependence(owner, connection);
                }
            } catch (Exception e) {
                throw new DaoException("Exception on save dependence in relation " + m + ".", e);
            }
        }
    }
}
