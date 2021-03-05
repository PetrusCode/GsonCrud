package com.netmind.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.netmind.dao.contracts.StudentDao;
import com.netmind.model.Student;

public class StudentDaoImpl implements StudentDao {
	private static ArrayList<Student> arrayStudent = null;

	static final Logger logger = Logger.getLogger(StudentDaoImpl.class);

	static {
		arrayStudent = new ArrayList<Student>();
	}

	@Override
	public boolean add(Student student) {
		logger.info("add method called");
		for (int iterator = 0; iterator < arrayStudent.size(); iterator++) {
			System.out.println(arrayStudent.get(iterator));

		}
		try {
			addStudentsinFile(student);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arrayStudent.add(student);

	}

	@Override
	public boolean addStudentsinFile(Student student) throws IOException {
		logger.info("addStudentsinFile method called");
		@SuppressWarnings("unused")
		StudentDao studentDao = new StudentDaoImpl();

		try (FileWriter writer = new FileWriter(
				"./txtDb/" + FileManagementsDao.getFileName(), true);
				BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
			bufferedWriter.write(student.toTextFile());
			bufferedWriter.write(System.lineSeparator());

		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage() + student.toString());
			throw e;
		}

		return true;
	}

	@Override
	public boolean addToJsonFile(Student student) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
}