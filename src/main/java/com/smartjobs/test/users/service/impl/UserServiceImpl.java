package com.smartjobs.test.users.service.impl;

import com.smartjobs.test.users.domain.dto.UserDTO;
import com.smartjobs.test.users.domain.dto.UserMapper;
import com.smartjobs.test.users.domain.dto.UserRequest;
import com.smartjobs.test.users.domain.repository.UserRepository;
import com.smartjobs.test.users.service.PhoneService;
import com.smartjobs.test.users.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PhoneService phoneService;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PhoneService phoneService, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.phoneService = phoneService;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public UserDTO create(UserRequest userRequest, String authorization) {
		var newUser = userMapper.toModel(userRequest);
		newUser.setIsactive(true);
		newUser.setToken(authorization.substring(7));
		newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		newUser = userRepository.save(newUser);
		if(userRequest.getPhones() != null)
			phoneService.createAllByUser(newUser, userRequest.getPhones());
		return userMapper.toDto(newUser);
	}

	@Override
	public Page<UserDTO> getAll(Pageable pageable) {
		return  userRepository.findAll(pageable).map(userMapper::toDto);
	}
}
