package com.university.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.university.domain.entity.Group;
import com.university.exception.DaoException;
import com.university.util.HibernateUtil;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDaoImpl extends GenericDAOImpl<Group, Integer> {
	
	@Autowired
    private HibernateUtil hibernateUtil;
	
	public Group findByGroupNumber(String groupNumber) {
		Group group = null;
        String sql = "SELECT a FROM university_groups a WHERE a.group_number = " + groupNumber;
        group = (Group) hibernateUtil.fetchAll(sql);
        return group;
    }
	@Override
	public Group findByID(Integer id) throws DaoException {
		return hibernateUtil.fetchById(id,  Group.class);
	}

}
