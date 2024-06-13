package com.smartjobs.test.users.handler;

import com.smartjobs.test.users.domain.dto.ApiResponseErrorDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiResponseErrorDto> handleValidationExceptions(MethodArgumentNotValidException exception){
		var listError  =exception.getFieldErrors().stream()
				.map( error -> error.getField()+" "+error.getDefaultMessage())
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(createApiResponseErrorDto(String.join(",", listError)));
	}


	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiResponseErrorDto> handleException(Exception exception){
		exception.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(
						createApiResponseErrorDto(exception.getMessage())
						);

	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public ResponseEntity<ApiResponseErrorDto> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
				.body(
						createApiResponseErrorDto("Email ya fue registrado")
						);

	}

	private ApiResponseErrorDto createApiResponseErrorDto(String message) {
		return ApiResponseErrorDto.builder()
				.message(message)
				.build();
	}
}
