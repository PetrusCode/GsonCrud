package com.netmind.presentation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.netmind.business.StudentBlImpl;
import com.netmind.business.contracts.StudentBl;
import com.netmind.common.model.EnumStudent;
import com.netmind.common.model.Student;
import com.netmind.dao.StudentDaoImpl;
import com.netmind.dao.contracts.StudentDao;

public class Menu {

	public static void studentMenu() throws IOException {

		StudentDao studentDao = new StudentDaoImpl();
		StudentBl studentBl = new StudentBlImpl();
		Scanner scanner = new Scanner(System.in);
		EnumStudent enumStudent = null;
		int opcion = 0;

		do {

			try {

				opcion = Integer.parseInt(JOptionPane
						.showInputDialog("Que opcion quiere seleccionar?"
								+ "\n 1.Agregar \n 2.Calular estudiante con mayor "
								+ "edad \n 3.Calular la media de edad \n 4.Exit"));

			} catch (Exception e) {
				System.out.println("Introduzca un numero " + e);

			}
			enumStudent = EnumStudent.desdeValue(opcion);
			switch (enumStudent) {

			case ADD_STUDENT:
				Student student = new Student();
				addnewStudents(student, scanner);
				studentBl.add(student);
				break;
			case CALCULATE_STUDENT_MAX_AGE:
				System.out.println("Calcular el estudiante con mayor edad");
				break;
			case CALCULATE_AVERAGE_AGE:
				System.out.println(
						"Calcular la media de edad de todos los estudiantes ");
				break;

			default:
				break;
			}
		} while (opcion != EnumStudent.EXIT.value());
		System.out.println("Salir");
		scanner.close();
	}

	private static void addnewStudents(Student student, Scanner scanner)
			throws IOException {
		System.out.println("Agregar nuevo estudiante");

		System.out.println("Nombre");
		student.setName(scanner.nextLine());

		System.out.println("Apellido");
		student.setSurname(scanner.nextLine());
		System.out.println("Introduce fecha de nacimiento - (yyyy-mm-dd)");

		student.setDateOfBirth(LocalDate.parse(scanner.nextLine()));
		/*
		 * System.out.println(
		 * "Introduce nombre del archivo (.txt, .json, .xml...or default");
		 * 
		 * try {
		 * 
		 * FileManagementsDao.addTxtStudent("./txtDb/" + scanner.nextLine());
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); throw e; }
		 */

	}
}
