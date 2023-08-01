package com.bootcamp.converter;

import com.bootcamp.controller.request.RequestForCreateUser;
import com.bootcamp.controller.responce.UserResponse;
import com.bootcamp.domain.User;
import org.mapstruct.Mapper;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {

    User mapToCreate(RequestForCreateUser userRequest);

    UserResponse mapToResponse(User user);
}
