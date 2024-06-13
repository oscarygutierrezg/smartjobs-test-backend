package com.smartjobs.test.users.impl;


import com.smartjobs.test.users.domain.dto.PhoneRequest;
import com.smartjobs.test.users.domain.dto.UserMapper;
import com.smartjobs.test.users.domain.dto.UserRequest;
import com.smartjobs.test.users.domain.model.User;
import com.smartjobs.test.users.domain.repository.UserRepository;
import com.smartjobs.test.users.service.PhoneService;
import com.smartjobs.test.users.service.impl.UserServiceImpl;
import com.smartjobs.test.users.util.PhoneUtil;
import com.smartjobs.test.users.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;
	@Spy
	private UserMapper userMapper;
	@Spy
	private PasswordEncoder passwordEncoder;
	@Mock
	private UserRepository userRepository;
	@Mock
	private PhoneService phoneService;

	private final PhoneUtil phoneUtil = new PhoneUtil();
	private final UserUtil userUtil = new UserUtil();


	@Test
	public void test_CreateAllByUser_Should_CreatedUser_When_Invoked() {
		Mockito.when(userMapper.toModel(Mockito.any(UserRequest.class))).thenAnswer(p -> userUtil.toModel((UserRequest) p.getArguments()[0]));
		Mockito.when(userMapper.toDto(Mockito.any(User.class))).thenAnswer(p -> userUtil.toDto((User) p.getArguments()[0]));
		Mockito.doNothing().when(phoneService).createAllByUser(Mockito.any(User.class), Mockito.any(List.class));
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(
				userUtil.createUser());

		List<PhoneRequest> phones = List.of(
				phoneUtil.createPhoneRequest(),
				phoneUtil.createPhoneRequest(),
				phoneUtil.createPhoneRequest());

		var user = userUtil.createUserRequest();
		user.setPhones(phones);
		var newUser = userService.create(user, "Bearer eyJhbGciOiJIUzI1NiJ9.");

		Assertions.assertNotNull(newUser);
		Assertions.assertNotNull(newUser.getId());
		Mockito.verify(phoneService, Mockito.times(1)).createAllByUser(Mockito.any(), Mockito.any());
		Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
	}

	@Test
	public void test_NotCreateAllByUser_Should_CreatedUser_When_Invoked() {
		Mockito.when(userMapper.toModel(Mockito.any(UserRequest.class))).thenAnswer(p -> userUtil.toModel((UserRequest) p.getArguments()[0]));
		Mockito.when(userMapper.toDto(Mockito.any(User.class))).thenAnswer(p -> userUtil.toDto((User) p.getArguments()[0]));
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(
				userUtil.createUser());


		var user = userUtil.createUserRequest();
		var newUser = userService.create(user, "Bearer eyJhbGciOiJIUzI1NiJ9.");

		Assertions.assertNotNull(newUser);
		Assertions.assertNotNull(newUser.getId());
		Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
	}

	@Test
	public void test_CreateAllByUser_Should_ReturnListPhones_When_Invoked() {
		PageImpl<User> userPage = new PageImpl<User>(List.of(
				userUtil.createUser(),
				userUtil.createUser(),
				userUtil.createUser()));
		Mockito.when(userRepository.findAll(Mockito.any(Pageable.class))).thenReturn(userPage);


		var user = userUtil.createUser();

		Pageable pageable = PageRequest.of(0, 10);
		var usersPage = userService.getAll(pageable);


		Assertions.assertNotNull(usersPage);
		Assertions.assertNotNull(usersPage.getContent());
		Assertions.assertEquals(3,usersPage.getContent().size());
		Mockito.verify(userRepository, Mockito.times(1)).findAll(Mockito.any(Pageable.class));
	}






}
