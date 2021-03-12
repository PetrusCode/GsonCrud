package com.netmind.dao;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.naming.CompoundName;
import javax.naming.InvalidNameException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netmind.common.model.LocalDateSerializer;
import com.netmind.common.model.Student;
import com.netmind.dao.contracts.StudentDao;

public class StudentDaoImpl implements StudentDao {
	private static ArrayList<Student> arrayStudent = null;

	static final Logger logger = Logger.getLogger(StudentDaoImpl.class);

	static {
		arrayStudent = new ArrayList<Student>();
	}

	@Override
	public boolean add(Student student) {
		logger.info("add method called");
		/*
		 * for (int iterator = 0; iterator < arrayStudent.size(); iterator++) {
		 * System.out.println(arrayStudent.get(iterator));
		 * 
		 * }
		 */
		try {
			addStudentsinFile(student);
			addToJsonFile(student);
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
				FileManagementsDao.getFileName("txt"), true);
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
		logger.info("addToJsonFile method called");

		List<Student> studentList = getAllFromJson();
		studentList.add(student);
		@SuppressWarnings("unused")
		StudentDao studentDao = new StudentDaoImpl();

		try (Writer writer = new FileWriter(
				FileManagementsDao.getFileName("json"))) {

			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(LocalDate.class,
					new LocalDateSerializer());
			Gson gson = gsonBuilder.setPrettyPrinting().create();
			gson.toJson(studentList.toArray(), writer);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage() + student.toString());
			throw e;
		}

		return true;
	}

	/*
	 * //Otra forma de implementar y guardar en un archivo
	 * Json(version-anterior-de este programa)
	 * 
	 * @Override public boolean addToJsonFileOld(Student student) throws
	 * IOException { logger.info("addToJsonFile method called");
	 * 
	 * @SuppressWarnings("unused") StudentDao studentDao = new StudentDaoImpl();
	 * 
	 * try (JsonWriter writer = new JsonWriter( new FileWriter("alumno.json")))
	 * {
	 * 
	 * writer.beginObject();
	 * writer.name("UUID").value(student.getUuid().toString());
	 * writer.name("Id").value(student.getIdStudent());
	 * writer.name("Nombre").value(student.getName());
	 * writer.name("Apellido").value(student.getSurname());
	 * writer.name("edad").value(student.getAge());
	 * writer.name("Fecha de nacimiento")
	 * .value(student.getDateOfBirth().toString()); writer.endObject();
	 * 
	 * } catch (IOException e) { e.printStackTrace();
	 * logger.error(e.getMessage() + student.toString()); throw e; }
	 * 
	 * return true; }
	 */
	public Enumeration getAll() throws InvalidNameException {
		Properties props = new Properties();
		props.put("jndi.syntax.separator", ":");
		props.put("jndi.syntax.direction", "left_to_right");

		// create compound name object
		CompoundName compoundName = new CompoundName("a:b:z:y:x", props);

		Enumeration<String> components = compoundName.getAll();

		// print value
		int i = 0;
		while (components.hasMoreElements()) {
			System.out
					.println("position " + i + " :" + components.nextElement());
			i++;

		}
		System.out.println(components);
		return components;

	}

	@Override
	public List<Student> getAllFromJson() {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		List<Student> yourClassList = null;
		try (Reader reader = new FileReader(
				FileManagementsDao.getFileName("json"))) {
			// Convert JSON File to Java Object
			yourClassList = new Gson().fromJson(reader, ArrayList.class);
			if (!yourClassList.isEmpty()) {
				return yourClassList;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<Student>();
	}

}
