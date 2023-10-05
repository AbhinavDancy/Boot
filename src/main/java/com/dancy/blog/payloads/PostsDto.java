package com.dancy.blog.payloads;

import java.time.LocalDate;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//here we have taken fields which are required by the user ,
//its our choice to take the user and category obj. but we will go through the Url method of sending data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostsDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String content;
	private String title;
	private LocalDate date;
	private String image;
	private CategoryDto cat;
	private UserDto user;
	
	
	
	
}
