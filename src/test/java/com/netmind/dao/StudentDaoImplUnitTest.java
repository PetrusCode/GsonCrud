package com.netmind.dao;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.netmind.common.model.Student;
import com.netmind.dao.contracts.StudentDao;

@RunWith(MockitoJUnitRunner.class)
public class StudentDaoImplUnitTest {

	@Mock
	private StudentDao studentDao;

	private ArrayList<Student> studentList = new ArrayList<Student>();

	Student student;
	Student student1;

	@Before
	public void setUp() throws IOException {
		MockitoAnnotations.initMocks(this);

		student = new Student();
		student.setIdStudent(1);
		student.setName("pedro");
		student.setSurname("ferrer");
		student.setAge(20);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dateofbirh = LocalDate.parse("21-02-1999", formatter);
		student.setDateOfBirth(dateofbirh);

		student1 = new Student();
		student1.setIdStudent(1);
		student1.setName("pedro");
		student1.setSurname("ferrer");
		student1.setAge(20);
		DateTimeFormatter formatter1 = DateTimeFormatter
				.ofPattern("dd-MM-yyyy");
		LocalDate dateofbirh2 = LocalDate.parse("21-02-1999", formatter1);
		student1.setDateOfBirth(dateofbirh2);

		studentList.add(student);
		studentList.add(student1);

		when(studentDao.getAllFromJson()).thenReturn(studentList);
		when(studentDao.updateToJsonFile(student)).thenReturn(true);
		when(studentDao.addToJsonFile(student)).thenReturn(true);
		when(studentDao.removeFromJsonFile(1)).thenReturn(true);

	}

	@Test
	public void testGetAllFromJson() throws IOException {
		List<Student> studentList = studentDao.getAllFromJson();
		verify(studentDao, never()).add(student);
		verify(studentDao, never()).add(student1);
		assertTrue("El tama�o de la lista no coincide",
				studentList.size() == 2);

	}

	@Test
	public void testupdateToJsonFile() throws IOException {
		assertTrue("El estudiante no se ha actualizado correctamente",
				studentDao.updateToJsonFile(student) == true);
	}

	@Test
	public void testaddToJsonFile() throws IOException {
		assertTrue("El estudiante no se ha agregado correctamente",
				studentDao.addToJsonFile(student) == true);
	}

	@Test
	public void testremoveFromJsonFile() throws IOException {
		assertTrue("El estudiante no se ha eliminado correctamente",
				studentDao.removeFromJsonFile(1) == true);
	}

}
