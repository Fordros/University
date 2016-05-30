package com.university.domain.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.university.dao.impl.GroupDaoImpl;
import com.university.domain.entity.Group;
import com.university.exception.DomainException;

@Service
@Transactional
public class GroupService {
	final static Logger logger = Logger.getLogger(GroupService.class);

	@Autowired
	private GroupDaoImpl groupDao;

	public List<Group> getAll() {
		try {
			return  groupDao.findAll(Group.class);
		} catch (HibernateException e) {
			logger.error("Error when trying to find all groups", e);
			throw new DomainException("Error when trying to find all groups", e);
		}
	}

	public Group findByGroupNumber(String groupNumber) {
		try {
			return groupDao.findByGroupNumber(groupNumber);
		} catch (HibernateException e) {
			logger.error("Error when trying to find group by group number", e);
			throw new DomainException("Error when trying to find group by group number",
					e);
		}
	}

	public Group findById(Integer id) {
		try {
			return groupDao.findByID(Group.class, id);
		} catch (HibernateException e) {
			logger.error("Error when trying to find group by id", e);
			throw new DomainException("Error when trying to find group by id",
					e);
		}
	}

	public void addNew(Group group) {
		try {
			groupDao.save(group);
		} catch (HibernateException e) {
			logger.error("Error when to add a new group", e);
			throw new DomainException("Error when to add a new group", e);
		}
	}

	public void delete(Group group) {
		try {
			groupDao.delete(group);
		} catch (HibernateException e) {
			logger.error("Error when to try to remove group", e);
			throw new DomainException("Error when to try to remove group", e);
		}

	}

	public void update(Group group) {
		try {
			groupDao.merge(group);
		} catch (HibernateException e) {
			logger.error("Error when to try to update group", e);
			throw new DomainException("Error when to try to update group", e);
		}

	}

}
