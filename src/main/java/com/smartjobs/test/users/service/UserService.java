package com.smartjobs.test.users.service;

import com.smartjobs.test.users.domain.dto.UserDTO;
import com.smartjobs.test.users.domain.dto.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
	UserDTO create(UserRequest userRequest, String uuthorization);

	Page<UserDTO> getAll(Pageable pageable);
}
