package com.netmind.dao;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.netmind.business.StudentBlImpl;
import com.netmind.business.contracts.StudentBl;
import com.netmind.dao.contracts.StudentDao;
import com.netmind.model.Student;

class StudentDaoImplIntgrationTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddToJsonFile() throws IOException {
		StudentDao studentDao = new StudentDaoImpl();
		Student student = new Student();

		studentDao.add(student);
		fail("Not yet implemented");
	}

	@Test
	void addStudentToFile() throws IOException {
		StudentBl studentBl = new StudentBlImpl();
		Student student = new Student();
		student.setIdStudent(1);
		student.setName("Pepe");
		student.setSurname("Soto");
		student.setAge(45);
		student.setDateOfBirth(LocalDate.parse("1975-04-10"));

		studentBl.add(student);
	}
}
