package com.nextera.blogapp.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty
	private String name;
	@Email(message="invalid email !")
	private String email;
	@NotNull
	@Size(min=2, max=8 ,message="Password must be atleast 2 chas and atmost 8 chars!")
	private String password;
	@NotNull
	private String about;
}
