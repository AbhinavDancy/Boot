package com.dancy.blog.exception;

public class ResourceNotFoundException extends RuntimeException {
	private String resourceName;
	private String fieldName;
	private int fieldValue;
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
		super(String.format(" %s with %s %s not found",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
