package com.smartjobs.test.users.util;

import com.github.javafaker.Faker;
import com.smartjobs.test.users.domain.dto.LoginRequest;
import com.smartjobs.test.users.domain.dto.UserDTO;
import com.smartjobs.test.users.domain.dto.UserRequest;
import com.smartjobs.test.users.domain.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserUtil {

	private Faker faker = new Faker();
	public UserDTO toDto(User user) {
		return  UserDTO.builder()
				.name(user.getName())
				.token(user.getToken())
				.password(user.getPassword())
				.isactive(user.getIsactive())
				.email(user.getEmail())
				.created(user.getCreated())
				.modified(user.getModified())
				.lastLogin(user.getLastLogin())
				.id(user.getId())
				.build();
	}

	public User  toModel(UserRequest user) {
		var newUser  = new User();
		newUser.setName(user.getName());
		newUser.setPassword(user.getPassword());
		newUser.setEmail(user.getEmail());
		return newUser;
	}

	public User createUser( ) {
		User user = new User();
		user.setName(faker.name().name());
		user.setToken(faker.internet().password());
		user.setPassword(faker.internet().password());
		user.setIsactive(true);
		user.setEmail(faker.internet().emailAddress());
		user.setCreated(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		user.setId(UUID.randomUUID());
		return user;
	}

	public UserRequest createUserRequest( ) {
		return  UserRequest.builder().
		name(faker.name().name()).
		password(faker.internet().password()).
		email(faker.internet().emailAddress())
		.build();
	}

	public LoginRequest createUsuarioRequestLogin() {
		return  LoginRequest.builder()
				.email("test@test.com")
				.password("test")
				.build();
	}
}
