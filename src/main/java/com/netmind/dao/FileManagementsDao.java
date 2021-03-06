package com.netmind.dao;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

public class FileManagementsDao {

	private static File file = null;

	private static HashMap<String, File> fileType = new HashMap<String, File>();

	public static synchronized boolean addTxtStudent(String Filename) throws IOException {
		boolean ifFileIsCreated = false;
		file = new File(Filename);

		if (!file.exists()) {
			try {
				if (ifFileIsCreated = file.createNewFile()) {
					if (Filename.contains(".txt")) {
						fileType.put(".txt", file);

					} else if (Filename.contains(".json")) {
						fileType.put(".json", file);
					}
				}
				System.out.println("Se ha creado el archivo" + Filename);

			} catch (IOException e) {
				System.out.println(e.getMessage());
				throw e;
			}

		} else {

			try {
				if (ifFileIsCreated = file.createNewFile()) {
					if (Filename.contains(".txt")) {
						fileType.put("", file);

					} else if (Filename.contains(".json")) {
						fileType.put("", file);
					}
				}
				System.out.println("Se ha agregado un nuevo registro" + Filename);

			} catch (IOException e) {
				System.out.println(e.getMessage());
				throw e;
			}
		}

		return ifFileIsCreated;

	}

	public static String getFileName(String type) {
		file = fileType.get(type);

		return file.getName();
	}

}
