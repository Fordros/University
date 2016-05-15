package com.university.dao.impl;


import com.university.dao.AbstractJDBCDao;
import com.university.dao.DaoFactory;
import com.university.domain.Group;
import com.university.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GroupDaoImpl extends AbstractJDBCDao<Group, Integer> {

	private DaoFactory<Connection> parentFactory;
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet rs = null;

	 public GroupDaoImpl(DaoFactory<Connection> parentFactory, Connection connection) {
	        super(parentFactory, connection);
	    }

    private class PersistGroup extends Group {
        public void setId(int id) {
            super.setId(id);
        }
    }


    @Override
    public String getSelectQuery() {
        return "SELECT id, number_group FROM \"Group\"";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO \"Group\" (number_group) \n" +
                "VALUES (?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE \"Group\" SET number_group= ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM \"Group\" WHERE id= ?;";
    }


    public String getFindByNumberQuery() {
        return "SELECT id FROM \"Group\" WHERE VALUES (?);";
    }

    @Override
    public Group create() throws DaoException {
        Group group = new Group();
        return persist(group);
    }



    @Override
    protected List<Group> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Group> result = new LinkedList<Group>();
        try {
            while (rs.next()) {
                PersistGroup group = new PersistGroup();
                group.setId(rs.getInt("id"));
                group.setGroupNumber(rs.getString("number_group"));
                result.add(group);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Group object) throws DaoException {
        try {
            statement.setString(1, object.getGroupNumber());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Group object) throws DaoException {
        try {
            statement.setString(1, object.getGroupNumber());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
    public Group  getByGroupNumber(Integer key) throws DaoException {
    	Group group = new Group();
        String sql = getFindByNumberQuery();
        try {
        	statement = connection.prepareStatement(sql);
            statement.setInt(1, key);
            rs = statement.executeQuery();
            group.setId(rs.getInt("id"));
            group.setGroupNumber(rs.getString("number_group"));
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

        return group;
    }
}
