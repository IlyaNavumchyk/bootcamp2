package com.bootcamp.repository;

import com.bootcamp.domain.Role;
import com.bootcamp.domain.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(UserRoles roleName);
}