package com.rtkit.fifth.element.kms.model.mapper.impl;

import com.rtkit.fifth.element.kms.model.dto.UserDto;
import com.rtkit.fifth.element.kms.model.entity.User;
import com.rtkit.fifth.element.kms.model.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public List<UserDto> modelToDto(List<User> entityList) {
        return null;
    }
}
