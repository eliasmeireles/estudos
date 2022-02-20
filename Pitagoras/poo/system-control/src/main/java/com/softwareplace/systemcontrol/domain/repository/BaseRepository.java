package com.softwareplace.systemcontrol.domain.repository;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softwareplace.systemcontrol.core.file.FileManager;
import com.softwareplace.systemcontrol.core.file.FileManagerBean;
import com.softwareplace.systemcontrol.domain.models.BaseModel;

public class BaseRepository<T extends BaseModel> {

	private final String modelDir;
	private final FileManager fileManager;
	private final Class<T> tClass;

	public BaseRepository(String modelDir, Class<T> tClass) {
		this.modelDir = modelDir;
		this.tClass = tClass;
		this.fileManager = FileManagerBean.jsonFileManager();
	}

	public T create(T model) {
		model.setId(UUID.randomUUID().toString());
		fileManager.createFile(modelDir.concat(File.separator).concat(model.getId()), model);
		return model;
	}

	public T update(T model) {
		model.setId(UUID.randomUUID().toString());
		return model;
	}

	public List<T> findAll() {
		try {
			return fileManager.loadAll(modelDir, tClass);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public T find(String id) {
		return null;
	}
}
