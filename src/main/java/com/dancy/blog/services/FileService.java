package com.dancy.blog.services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	String uploadImage(String Path, MultipartFile file ) throws IOException;
	InputStream getResource(String path, String fileName) throws IOException;

}
