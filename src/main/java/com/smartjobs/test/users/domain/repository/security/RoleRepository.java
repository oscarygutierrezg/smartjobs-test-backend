package com.smartjobs.test.users.domain.repository.security;

import com.smartjobs.test.users.domain.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
