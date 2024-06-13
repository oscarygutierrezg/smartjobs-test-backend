package com.smartjobs.test.users.domain.dto;

import com.smartjobs.test.users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(
		componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
		)
public
interface UserMapper {

	UserDTO toDto(User user);

	User toModel(UserRequest user);


	@Mapping(source = "phones", target = "phones")
	void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);
}
