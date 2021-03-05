package com.netmind.dao;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.netmind.dao.contracts.StudentDao;
import com.netmind.model.Student;

class StudentDaoImplIntgrationTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FileManagementsDao.addTxtStudent("alumno.json");
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

}
