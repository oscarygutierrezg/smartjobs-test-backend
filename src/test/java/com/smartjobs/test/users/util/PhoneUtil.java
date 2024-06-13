package com.smartjobs.test.users.util;

import com.github.javafaker.Faker;
import com.smartjobs.test.users.domain.dto.PhoneDTO;
import com.smartjobs.test.users.domain.dto.PhoneRequest;
import com.smartjobs.test.users.domain.model.Phone;

import java.time.LocalDateTime;
import java.util.UUID;

public class PhoneUtil {

	private Faker faker = new Faker();
	public PhoneDTO toDto(Phone user) {
		return  PhoneDTO.builder()
				.cityCode(user.getCityCode())
				.countryCode(user.getCountryCode())
				.number(user.getNumber())
				.build();
	}

	public Phone  toModel(PhoneRequest user) {
		var newPhone  = new Phone();
		newPhone.setCityCode(user.getCityCode());
		newPhone.setCountryCode(user.getCountryCode());
		newPhone.setNumber(user.getNumber());
		return newPhone;
	}

	public Phone createPhone( ) {
		Phone user = new Phone();
		user.setCityCode(faker.phoneNumber().extension());
		user.setCountryCode(faker.phoneNumber().subscriberNumber());
		user.setNumber(faker.phoneNumber().phoneNumber());
		user.setId(UUID.randomUUID());
		return user;
	}

	public PhoneRequest createPhoneRequest( ) {
		return PhoneRequest.builder().
				cityCode(faker.phoneNumber().extension())
				.countryCode(faker.phoneNumber().subscriberNumber())
				.number(faker.phoneNumber().phoneNumber())
				.build();
	}

}
