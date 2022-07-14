package com.rtkit.fifth.element.kms.model.mapper;

import com.rtkit.fifth.element.kms.model.dto.UserDto;
import com.rtkit.fifth.element.kms.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

    UserDto modelToDto(User user);
}
