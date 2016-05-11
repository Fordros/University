package com.univestity.entity;

import com.university.dao.DaoFactory;
import com.university.dao.impl.DaoFactoryImpl;
import com.university.domain.Group;
import com.university.domain.Student;
import com.university.exception.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class RelationTest {
	private static final DaoFactory<Connection> factory = new DaoFactoryImpl();

	private Connection connection;

	@Before
	public void setUp() throws DaoException, SQLException {
		connection = factory.getConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws SQLException {
		connection.rollback();
		connection.close();
	}

	@Test
	public void testCreate() throws DaoException {
		Student student = (Student) factory.getDao(Student.class)
				.create();
		Assert.assertNull("Group is not null.", student.getGroup());

		Group group = new Group();
		student.setGroup(group);
		Assert.assertNotNull("Group is null.", student.getGroup());
	}

	@Test
	public void testPersist() throws DaoException {
		Student student = new Student();
		Group group = (Group) factory.getDao( Group.class).create();
		group.setGroupNumber("1234");
		student.setGroup(group);
		student.setFirstName("ROST");
		student = (Student) factory.getDao( Student.class).persist(
				student);
		Assert.assertNotNull("Group is null.", student.getGroup());
		Assert.assertEquals("Wrong group number.", "1234", student.getGroup()
				.getGroupNumber());
	}

	@Test
	public void testPersistAll() throws DaoException {
		Student student = new Student();
		student.setGroup(new Group());
		student = (Student) factory.getDao( Student.class).persist(
				student);
		Assert.assertNotNull("Group is null.", student.getGroup());
		Assert.assertNotNull("Group.id is null.", student.getGroup().getId());
	}

	@Test
	public void testUpdate() throws DaoException {
		Student student = (Student) factory.getDao( Student.class)
				.create();
		student.setGroup(new Group());
		factory.getDao( Student.class).update(student);
		Assert.assertNotNull("Group is null.", student.getGroup());
		Assert.assertNotNull("Group.id is null.", student.getGroup().getId());
	}

	@Test
	public void testUpdateAll() throws DaoException {
		Student student = (Student) factory.getDao( Student.class)
				.create();
		Group group = (Group) factory.getDao( Group.class).create();
		group.setGroupNumber("1234");
		student.setGroup(group);
		factory.getDao(Student.class).update(student);
		Assert.assertNotNull("Group is null.", student.getGroup());
		Assert.assertEquals("Wrong group number.", "1234", student.getGroup()
				.getGroupNumber());
	}

	@Test
	public void testRead() throws DaoException {
		Student student = (Student) factory.getDao( Student.class)
				.create();
		student.setGroup(new Group());
		factory.getDao(Student.class).update(student);

		student = (Student) factory.getDao( Student.class).getByPK(
				student.getId());
		Assert.assertNotNull("Student is null.", student);
		Assert.assertNotNull("Group is null.", student.getGroup());
	}

	@Test
	public void testDelete() throws DaoException {
		Student student = (Student) factory.getDao( Student.class)
				.create();
		student.setGroup(new Group());
		factory.getDao( Student.class).update(student);

		Group group = student.getGroup();

		factory.getDao(Student.class).delete(student);
		group = (Group) factory.getDao( Group.class).getByPK(
				group.getId());
		Assert.assertNotNull("Group not found.", group);
	}
}
