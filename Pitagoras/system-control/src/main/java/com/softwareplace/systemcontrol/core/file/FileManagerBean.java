package com.softwareplace.systemcontrol.core.file;

public class FileManagerBean {

	private FileManagerBean() {

	}

	public static FileManager jsonFileManager() {
		return new JsonFileManager();
	}

	public static FileManager xmlFileManager() {
		return new XMLFileManager();
	}
}
