package com.dancy.blog.payloads;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDto {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		@NotBlank(message ="Category is empty")
		private String category;
		@NotBlank(message = "Category Description missing")
		private String categoryDescription;
		
		

	}
	
