package com.dancy.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDto {
	
	private int id;
	@NotBlank(message = "Name should have minimum of 2 character")
	private String name;
	@Email
	private String email;
	@NotBlank
	private String password;
	@NotBlank(message ="About the User in empty")
	private String about;
	@NotBlank(message = "userType should be User or Admin")
	private String userType;
}
