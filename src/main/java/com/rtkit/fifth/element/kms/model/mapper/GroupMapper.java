package com.rtkit.fifth.element.kms.model.mapper;

import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.entity.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper extends EntityMapper<
        GroupDto, Group> {

    GroupDto modelToDto(Group group);
}
