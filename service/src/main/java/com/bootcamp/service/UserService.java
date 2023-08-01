package com.bootcamp.service;

import com.bootcamp.domain.User;
import com.bootcamp.domain.UserRoles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> findAll(Pageable page);

    User create(User user, UserRoles roleName);
}
