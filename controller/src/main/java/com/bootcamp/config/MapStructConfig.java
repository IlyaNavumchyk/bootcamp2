package com.bootcamp.config;

import com.bootcamp.converter.RoleMapper;
import com.bootcamp.converter.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public UserMapper getUserMapper() {

        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public RoleMapper getRoleMapper() {

        return Mappers.getMapper(RoleMapper.class);
    }
}
