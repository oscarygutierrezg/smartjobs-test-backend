package com.smartjobs.test.users.domain.repository.security;

import com.smartjobs.test.users.domain.model.security.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
