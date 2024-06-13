package com.smartjobs.test.users.controller.impl;

import com.smartjobs.test.users.controller.UserController;
import com.smartjobs.test.users.domain.dto.UserDTO;
import com.smartjobs.test.users.domain.dto.UserRequest;
import com.smartjobs.test.users.service.UserService;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
public class UserControllerImpl  implements UserController {

	private final  UserService userService;

	public UserControllerImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ResponseEntity<UserDTO> create(
			@RequestHeader("Authorization") String authorization,
			@Valid @RequestBody  UserRequest userRequest) {
		return new ResponseEntity<>(userService.create(userRequest, authorization), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Page<UserDTO>>  getAll(
			Pageable pageable) {
		return new ResponseEntity<>(userService.getAll(pageable), HttpStatus.OK);
	}

}
