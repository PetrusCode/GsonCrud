package com.netmind.dao;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class FileManagementsDao extends Thread {
	private String fileName;

	private static File file = null;

	private static ConcurrentHashMap<String, File> fileType = new ConcurrentHashMap<String, File>();

	public FileManagementsDao() {

	}

	public FileManagementsDao(String filename) {
		this.fileName = filename;
	}

	public static synchronized boolean addTxtStudent(String fileName)
			throws IOException {
		/*
		 * boolean ifFileIsCreated = false; file = new File(fileName);
		 * 
		 * try { if (file.createNewFile()) { if (fileName.contains(".txt")) {
		 * fileType.put(".txt", file);
		 * 
		 * } else if (fileName.contains(".json")) { fileType.put(".json", file);
		 * } } System.out.println("Se ha creado el archivo" + fileName);
		 * 
		 * } catch (IOException e) { System.out.println(e.getMessage()); throw
		 * e; }
		 * 
		 * /* else {
		 * 
		 * try { if (ifFileIsCreated = file.createNewFile()) { if
		 * (Filename.contains(".txt")) { fileType.put("", file);
		 * 
		 * } else if (Filename.contains(".json")) { fileType.put("", file); } }
		 * System.out .println("Se ha agregado un nuevo registro" + Filename);
		 * 
		 * } catch (IOException e) { System.out.println(e.getMessage()); throw
		 * e; } }
		 * 
		 * 
		 * return ifFileIsCreated;
		 */
		boolean isFileCreated = false;
		int twoDataFiles = 2;

		file = new File(fileName);

		try {
			if (file.createNewFile()) {
				System.out.println("File is created!");
				if (fileName.contains(".txt")) {
					fileType.put("txt", file);
				} else if (fileName.contains(".json")) {
					fileType.put("json", file);
				}

			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (fileType.size() < twoDataFiles) {
			if (fileName.contains(".txt")) {
				fileType.put("txt", file);
			} else if (fileName.contains(".json")) {
				fileType.put("json", file);
			}
		}

		return isFileCreated;

	}

	public static String getFileName(String type) {
		return fileType.get(type).getName();
	}

	@Override
	public void run() {
		int twoDataFiles = 2;

		try {
			Thread.sleep(5000);
			file = new File(fileName);
			if (!file.exists()) {
				try {
					file.createNewFile();

				} catch (IOException e) {
					System.out.println(e.getMessage());
					throw e;
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		if (fileType.size() < twoDataFiles) {
			if (fileName.contains(".txt")) {
				fileType.put("txt", file);
			} else if (fileName.contains(".json")) {
				fileType.put("json", file);
			}
		}
	}

}
