package com.dancy.blog.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		String fileName = file.getOriginalFilename();
		String fullPath = path + File.separator + fileName ;
		File f = new File(fileName);
		if(!f.exists())
			f.mkdir();
		Files.copy(file.getInputStream(), Paths.get(fullPath));
		
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
