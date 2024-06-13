package com.smartjobs.test.users.controller;

import com.smartjobs.test.users.domain.dto.UserDTO;
import com.smartjobs.test.users.domain.dto.UserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User Controller", description = "Se encarga de manejar los usuarios.")
@RequestMapping(value = "/v1/users")
@CrossOrigin
@Validated
public interface UserController {

	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@Operation(summary = "Crea un nuevo usuario", description = "Este servicio es el encardo de la creacion de un nuevo usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuario creado "),
			@ApiResponse(responseCode = "400", description = "Errores en body"),
			@ApiResponse(responseCode = "412", description = "Error en email"),
			@ApiResponse(responseCode = "500", description = "Error interno en el servidor al intentar crear el usuario")})
	ResponseEntity<UserDTO> create(
			@RequestHeader("Authorization") String authorization,
			@Valid @RequestBody  UserRequest userRequest);

	@Operation(summary = "Obtiene todos los usuarios", description = "Este servicio es el encardo de obtener todos los usuaios registrados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listado de usuario "),
			@ApiResponse(responseCode = "500", description = "Error interno en el servidor al intentar listar los usuarios")})

	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<Page<UserDTO>>  getAll(Pageable pageable);

}
