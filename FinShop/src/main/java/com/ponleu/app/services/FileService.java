package com.ponleu.app.services;

public interface FileService {

	public boolean saveProductImge(String filename, String rawStringBase64);

	public boolean saveStringBase64ToFile(String pathname, String rawStringBase64);

	public String getFileExtension(String fileName);
}
