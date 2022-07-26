package com.rtkit.fifth.element.kms.model.mapper.impl;

import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.entity.Group;
import com.rtkit.fifth.element.kms.model.mapper.GroupMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupMapperImpl implements GroupMapper {
    @Override
    public List<GroupDto> modelToDto(List<Group> entityList) {
        return null;
    }
}
