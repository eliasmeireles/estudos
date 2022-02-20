package com.softwareplace.systemcontrol.core.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface FileManager {

	String BASE_FILE_DIR = System.getProperty("user.home")
			.concat(File.separator)
			.concat(".system_control")
			.concat(File.separator);

	<T> boolean createFile(String pathDir, T model);

	<T> List<T> loadAll(String pathDir,  Class<T> tClass) throws JsonProcessingException;

	List<File> readFiles(String pathDir) throws IOException;

	default BufferedWriter getBufferedWriter(String filePath) throws IOException {
		File file = new File(BASE_FILE_DIR.concat(filePath));
		if (!file.exists() && !file.createNewFile()) {
			throw new IOException(String.format("Failed to creat file %s", file));
		}
		FileOutputStream outputStream = new FileOutputStream(file);
		OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);

		return new BufferedWriter(streamWriter);
	}

	default List<String> loadContents(String pathDir) {
		File file = new File(pathDir);
		if (file.exists()) {
			//noinspection ResultOfMethodCallIgnored
			file.mkdirs();
		}
		File[] listFiles = new File(BASE_FILE_DIR.concat(pathDir)).listFiles();

		if (listFiles != null) {
			return Arrays.stream(listFiles).map(this::loadFileContent)
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	default String loadFileContent(File file) {
		try (BufferedReader reader = Files.newBufferedReader(Path.of(file.getPath()))) {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
