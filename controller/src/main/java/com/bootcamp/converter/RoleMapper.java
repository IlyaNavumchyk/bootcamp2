package com.bootcamp.converter;

import com.bootcamp.controller.responce.RoleResponse;
import com.bootcamp.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RoleMapper {

    @Mapping(target = "roleName", expression =
            "java(role.getRoleName().toString().replace(\"ROLE_\", \"\"))")
    RoleResponse mapToResponse(Role role);
}
