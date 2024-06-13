package com.smartjobs.test.users.service.impl;

import com.smartjobs.test.users.domain.dto.PhoneDTO;
import com.smartjobs.test.users.domain.dto.PhoneMapper;
import com.smartjobs.test.users.domain.dto.PhoneRequest;
import com.smartjobs.test.users.domain.model.User;
import com.smartjobs.test.users.domain.repository.PhoneRepository;
import com.smartjobs.test.users.service.PhoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

	private final  PhoneRepository phoneRepository;
	private final  PhoneMapper phoneMapper;

	public PhoneServiceImpl(PhoneRepository phoneRepository, PhoneMapper phoneMapper) {
		this.phoneRepository = phoneRepository;
		this.phoneMapper = phoneMapper;
	}

	@Override
	public void createAllByUser(User newUser, List<PhoneRequest> phones) {
		phoneRepository.saveAll(phoneMapper.toModels(phones).stream().map(phone -> {
			phone.setUser(newUser);
			return phone;
		}).toList());
	}

	@Override
	public List<PhoneDTO> getByUser(User user) {
		return phoneRepository.getByUser(user).stream().map(phoneMapper::toDto).toList();
	}

}
