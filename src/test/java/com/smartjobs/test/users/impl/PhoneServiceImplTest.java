package com.smartjobs.test.users.impl;


import com.smartjobs.test.users.domain.dto.PhoneMapper;
import com.smartjobs.test.users.domain.dto.PhoneRequest;
import com.smartjobs.test.users.domain.model.Phone;
import com.smartjobs.test.users.domain.model.User;
import com.smartjobs.test.users.domain.repository.PhoneRepository;
import com.smartjobs.test.users.service.impl.PhoneServiceImpl;
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

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class PhoneServiceImplTest {

	@InjectMocks
	private PhoneServiceImpl phoneService;
	@Spy
	private PhoneMapper phoneMapper;
	@Mock
	private PhoneRepository phoneRepository;

	private final PhoneUtil phoneUtil = new PhoneUtil();
	private final UserUtil userUtil = new UserUtil();


	@Test
	public void test_CreateAllByUser_Should_CreatedListPhones_When_Invoked() {
		Mockito.when(phoneRepository.saveAll(Mockito.any(List.class))).thenReturn(List.of(
				phoneUtil.createPhone(),
				phoneUtil.createPhone(),
				phoneUtil.createPhone()));

		List<PhoneRequest> phones = List.of(
				phoneUtil.createPhoneRequest(),
				phoneUtil.createPhoneRequest(),
				phoneUtil.createPhoneRequest());

		var user = userUtil.createUser();

		phoneService.createAllByUser(user, phones);

		Mockito.verify(phoneRepository, Mockito.times(1)).saveAll(Mockito.any());
	}

	@Test
	public void test_CreateAllByUser_Should_ReturnListPhones_When_Invoked() {
		Mockito.when(phoneRepository.getByUser(Mockito.any(User.class))).thenReturn(List.of(
				phoneUtil.createPhone(),
				phoneUtil.createPhone(),
				phoneUtil.createPhone()));


		var user = userUtil.createUser();

		var phones = phoneService.getByUser(user);


		Assertions.assertNotNull(phones);
		Assertions.assertEquals(3,phones.size());
		Mockito.verify(phoneRepository, Mockito.times(1)).getByUser(Mockito.any());
	}






}
