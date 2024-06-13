package com.smartjobs.test.users.domain.dto;

import com.smartjobs.test.users.domain.model.Phone;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserDTO {

	private UUID id;
	private String name;
	private String email;
	private String password;
	private List<PhoneDTO> phones;
	private String token;
	private Boolean isactive;
	private LocalDateTime created;
	private LocalDateTime modified;
	private LocalDateTime lastLogin;
}
