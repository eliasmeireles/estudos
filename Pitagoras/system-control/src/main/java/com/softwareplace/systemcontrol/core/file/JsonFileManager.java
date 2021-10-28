package com.softwareplace.systemcontrol.core.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softwareplace.systemcontrol.core.util.JsonUtil;
import com.softwareplace.systemcontrol.domain.models.BaseModel;

public class JsonFileManager implements FileManager {

	@Override public <T> boolean createFile(String filePath, T model) {
		try (BufferedWriter writer = getBufferedWriter(String.format("%s.json", filePath))) {
			writer.write(toJson(model));
			writer.newLine();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override public List<File> readFiles(String pathDir) throws IOException {
		return null;
	}

	@Override public <T> List<T> loadAll(String pathDir, Class<T> tClass) throws JsonProcessingException {
		List<String> strings = loadContents(pathDir);
		List<T> models = new ArrayList<>();
		for (String content : strings) {
			try {
				models.add(fromJson(content, tClass));
			} catch (Exception e) {

			}
		}
		return models;
	}

	private <T> String toJson(T model) throws JsonProcessingException {
		return JsonUtil.objectMapper()
				.writeValueAsString(model);
	}

	private <T> T fromJson(String content, Class<T> tClass) throws JsonProcessingException {
		//		return null;
		return JsonUtil.objectMapper().readValue(content, tClass);
	}
}
