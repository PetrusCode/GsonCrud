package com.netmind.dao;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
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

		writeToJson(studentList);

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

	@Override
	public ArrayList<Student> getAllFromJson() {
		// TODO Auto-generated method stub
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class,
				new JsonDeserializer<LocalDate>() {

					@Override
					public LocalDate deserialize(JsonElement json, Type typeOfT,
							JsonDeserializationContext context)
							throws JsonParseException {

						DateTimeFormatter formatter = DateTimeFormatter
								.ofPattern("dd-MM-yyyy");

						LocalDate localdate = LocalDate
								.parse(json.getAsString(), formatter);

						return localdate;
					}

				}).create();
		ArrayList<Student> studentList = null;
		try (Reader reader = new FileReader(
				FileManagementsDao.getFileName("json"))) {
			studentList = gson.fromJson(reader, new TypeToken<List<Student>>() {

			}.getType());
			if (studentList == null) {
				studentList = new ArrayList<Student>();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return studentList;

	}

	public boolean writeToJson(List<Student> studentJsonList)
			throws IOException {
		logger.info("addToJsonFile method called");

		try (Writer writer = new FileWriter(
				FileManagementsDao.getFileName("json"), false)) {

			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(LocalDate.class,
					new LocalDateSerializer());
			Gson gson = gsonBuilder.setPrettyPrinting().create();
			gson.toJson(studentJsonList.toArray(), writer);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage() + studentJsonList.toString());
			throw e;
		}

		return true;
	}

	@Override
	public boolean updateToJsonFile(Student student) throws IOException {
		List<Student> studentList = getAllFromJson();

		Student studentFiltered = studentList
				.stream().filter(studentLambda -> studentLambda
						.getIdStudent() == student.getIdStudent())
				.findFirst().get();

		studentFiltered.setIdStudent(student.getIdStudent());
		studentFiltered.setName(student.getName());
		studentFiltered.setSurname(student.getSurname());
		studentFiltered.setAge(student.getAge());
		studentFiltered.setDateOfBirth(student.getDateOfBirth());

		writeToJson(studentList);

		return true;
	}

	@Override
	public boolean removeFromJsonFile(Integer id) throws IOException {
		List<Student> studentJsonList = getAllFromJson();
		Student removedStudent = null;

		removedStudent = studentJsonList.stream()
				.filter(student -> student.getIdStudent().equals(id))
				.findFirst().get();

		if (removedStudent != null) {
			studentJsonList.remove(removedStudent);
			return writeToJson(studentJsonList);
		} else {
			return false;
		}
	}

}
