package com.netmind.dao.contracts;

import java.io.IOException;

import com.netmind.model.Student;

public interface StudentDao {
	public boolean add(Student student) throws IOException;

	public boolean addStudentsinFile(Student student) throws IOException;

	public boolean addToJsonFile(Student student) throws IOException;
}