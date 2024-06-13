package com.smartjobs.test.users.domain.dto;

import com.smartjobs.test.users.domain.dto.PhoneDTO;
import com.smartjobs.test.users.domain.dto.PhoneRequest;
import com.smartjobs.test.users.domain.model.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(
		componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
		)
public
interface PhoneMapper {

	PhoneDTO toDto(Phone phone);

	Phone toModel(PhoneRequest phone);

	List<PhoneDTO> toDtos(List<Phone> phones);

	List<Phone> toModels(List<PhoneRequest> phoneDTOs);
}
