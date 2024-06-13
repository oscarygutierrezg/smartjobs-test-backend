package com.smartjobs.test.users.service;

import com.smartjobs.test.users.domain.dto.PhoneDTO;
import com.smartjobs.test.users.domain.dto.PhoneRequest;
import com.smartjobs.test.users.domain.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhoneService {

	List<PhoneDTO> getByUser(User user);

	@Transactional
	void createAllByUser(User newUser, List<PhoneRequest> phones);
}
