package com.smartjobs.test.users.domain.dto;

import com.smartjobs.test.users.domain.dto.custom_validation.ValidPassword;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserRequest {

	@NotNull(message = "es obligatorio")
	@NotEmpty(message = "no debe ser vacío")
	@Size(min =1, max =50, message = "la cantidad de caracteres de estar entre 1 y 50")
	private String name;
	@NotNull(message = "es obligatorio")
	@NotEmpty(message = "no debe ser vacío")
	@Size(min =1, max =50, message = "la cantidad de caracteres de estar entre 1 y 50")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Formato de email no válido")
	private String email;
	@NotNull(message = "es obligatorio")
	@NotEmpty(message = "no debe ser vacío")
	@Size(min =1, max =50, message = "la cantidad de caracteres de estar entre 1 y 50")
	@ValidPassword
	private String password;
	private List<@Valid PhoneRequest> phones;
}
