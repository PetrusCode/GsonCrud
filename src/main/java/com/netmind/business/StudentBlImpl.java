package com.netmind.business;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.netmind.business.contracts.StudentBl;
import com.netmind.common.model.Student;
import com.netmind.common.util.Config;
import com.netmind.dao.FileManagementsDao;
import com.netmind.dao.StudentDaoImpl;
import com.netmind.dao.contracts.StudentDao;

public class StudentBlImpl implements StudentBl {
	Student student = new Student();
	static final Logger logger = Logger.getLogger(StudentBlImpl.class);
	static Properties prop = null;
	static InputStream input = null;
	static {
		prop = new Properties();

		try {
			input = StudentBlImpl.class
					.getResourceAsStream("/config.properties");
			prop.load(input);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}

	@Override
	public boolean add(Student student) throws IOException {

		StudentDao studentDao = new StudentDaoImpl();

		student.setAge(calculaAge(student.getDateOfBirth()));

		FileManagementsDao fileManagementsDaoTxtThread = new FileManagementsDao(
				Config.getTxtFileName());
		FileManagementsDao fileManagementsDaoJsonThread = new FileManagementsDao(
				Config.getJsonFileName());

		try {
			fileManagementsDaoTxtThread.start();
			fileManagementsDaoTxtThread.join();
			fileManagementsDaoJsonThread.start();
			fileManagementsDaoJsonThread.join();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		logger.info(Config.getTxtFileName());
		logger.info(Config.getJsonFileName());

		return studentDao.add(student);

	}

	public static int calculaAge(LocalDate dateOfBirth) {

		Period edad = Period.between(dateOfBirth, LocalDate.now());

		return edad.getYears();

	}

	@Override
	public boolean addToJsonFiles(Student student) throws IOException {
		StudentDao studentDao = new StudentDaoImpl();

		FileManagementsDao.addTxtStudent(prop.getProperty("JsonFilename"));

		logger.info("json file is created");
		return studentDao.addToJsonFile(student);
	}
}
