package com.rtkit.fifth.element.kms.model.mapper;

import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.entity.Group;
import com.rtkit.fifth.element.kms.model.entity.User;

import java.util.stream.Collectors;

//@Mapper(componentModel = "spring")
public interface GroupMapper extends EntityMapper<GroupDto, Group> {

    default GroupDto modelToDto(Group group) {
        return GroupDto.builder()
                .article(group.getArticles().stream().map(o ->
                        o.getArticle().getId()).collect(Collectors.toList()))
                .description(group.getDescription())
                .id(group.getId())
                .title(group.getTitle())
                .users(group.getUsers().stream().map(User::getId).collect(Collectors.toList()))
                .build();
    }
}
