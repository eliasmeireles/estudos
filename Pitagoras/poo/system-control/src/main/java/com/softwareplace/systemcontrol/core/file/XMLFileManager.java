package com.softwareplace.systemcontrol.core.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softwareplace.systemcontrol.domain.models.BaseModel;

public class XMLFileManager implements FileManager {

	@Override public <T> boolean createFile(String pathDir, T model) {
		return false;
	}

	@Override public List<File> readFiles(String pathDir) throws IOException {
		return null;
	}

	@Override public <T> List<T> loadAll(String pathDir,  Class<T> tClass) throws JsonProcessingException {
		return null;
	}
}
