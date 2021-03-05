package com.netmind.presentation;

import java.io.IOException;

public class Principal {

	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("java.version"));

		// Los text de integracion son los que prueban el codigo real y los
		// servicios reales

		System.out.println("Que opcion quiere seleccionar?"
				+ "\n 1.Agregar \n 2.Calular estudiante con mayor edad \n 3.Calular la media de edad \n 4.Exit");
		Menu.studentMenu();

	}

}
