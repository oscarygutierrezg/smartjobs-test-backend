package com.smartjobs.test.users.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartjobs.test.users.UsersApplication;
import com.smartjobs.test.users.domain.dto.ApiResponseErrorDto;
import com.smartjobs.test.users.domain.dto.UserDTO;
import com.smartjobs.test.users.domain.dto.UserRequest;
import com.smartjobs.test.users.domain.dto.jwt.JwtResponse;
import com.smartjobs.test.users.domain.repository.UserRepository;
import com.smartjobs.test.users.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UsersApplication.class)
class UserControllerTest {

	private static final String AUTHORIZATION = "Authorization";
	private  String token = "";

	@Autowired
	private MockMvc mockMvc;	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	private UserUtil userUtil = new UserUtil();

	@BeforeEach
	public void init() throws Exception {
		var userRequest = userUtil.createUsuarioRequestLogin();
		ResultActions res =    mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/security/authenticate")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userRequest))
						.accept(MediaType.APPLICATION_JSON)
		);

		JwtResponse jwtResponse = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), JwtResponse.class);
		token = jwtResponse.getToken();
	}

	@Test
	public void test_Create_Should_Created_When_Invoked() throws JsonProcessingException, Exception {
		UserRequest userRequest = userUtil.createUserRequest();
		ResultActions res  = mockMvc.perform(
						MockMvcRequestBuilders.post("/v1/users")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(userRequest))
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpectAll(
						MockMvcResultMatchers.status().isCreated()

				);

		UserDTO priceDto = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), UserDTO.class);
		Assertions.assertNotNull(priceDto);
		Assertions.assertNotNull(priceDto.getId());
	}


	@Test
	public void test_Create_Should_BadRequest_When_Invoked() throws JsonProcessingException, Exception {
		UserRequest userRequest = userUtil.createUserRequest();
		userRequest.setEmail("xcxzczxcxzxz");
		ResultActions res  = mockMvc.perform(
						MockMvcRequestBuilders.post("/v1/users")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(userRequest))
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpectAll(
						MockMvcResultMatchers.status().isBadRequest()

				);

		ApiResponseErrorDto errorDto = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), ApiResponseErrorDto.class);
		Assertions.assertNotNull(errorDto);
		Assertions.assertNotNull(errorDto.getMessage());
		Assertions.assertTrue(errorDto.getMessage().contains("email Formato de email no"));
	}


	@Test
	public void test_Create_Should_PreconditionFailed_When_Invoked() throws JsonProcessingException, Exception {
		var user = userUtil.createUser();
		userRepository.save(user);
		UserRequest userRequest = userUtil.createUserRequest();
		userRequest.setEmail(user.getEmail());
		ResultActions res  = mockMvc.perform(
						MockMvcRequestBuilders.post("/v1/users")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(userRequest))
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpectAll(
						MockMvcResultMatchers.status().isPreconditionFailed()

				);

		ApiResponseErrorDto errorDto = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), ApiResponseErrorDto.class);
		Assertions.assertNotNull(errorDto);
		Assertions.assertNotNull(errorDto.getMessage());
		Assertions.assertEquals("Email ya fue registrado", errorDto.getMessage());
	}


	@Test
	public void test_GetAll_Should_ReturnList_When_Invoked() throws JsonProcessingException, Exception {
		var user = userUtil.createUser();
		userRepository.save(user);
		mockMvc.perform(
						MockMvcRequestBuilders.get("/v1/users")
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpectAll(
						MockMvcResultMatchers.status().isOk()

				);
	}




}


