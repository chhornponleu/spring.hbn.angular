package com.ponleu.app.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ponleu.app.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	private static final Logger logger = Logger.getLogger(FileServiceImpl.class);

	@Autowired
	private Environment env;

	public boolean saveProductImge(String filename, String rawStringBase64) {
		String dir = env.getProperty("mvc.resource.products.location");
		return this.saveStringBase64ToFile(dir + filename, rawStringBase64);
	}

	public boolean saveStringBase64ToFile(String pathname, String rawStringBase64) {
		boolean result = true;

		rawStringBase64 = rawStringBase64.substring(rawStringBase64.indexOf(",") + 1);
		byte[] byteImage = Base64.getDecoder().decode(rawStringBase64);
		File file = new File(pathname);

		try {
			FileUtils.writeByteArrayToFile(file, byteImage);
		} catch (IOException e) {
			logger.error("Error writing file to " + pathname, e);
			result = false;
		}

		return result;
	}

	public String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
}
