package com.rtkit.fifth.element.kms.model.mapper;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.dto.NamespaceDto;
import com.rtkit.fifth.element.kms.model.dto.UserDto;
import com.rtkit.fifth.element.kms.model.entity.User;

import java.util.stream.Collectors;

//@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

    default UserDto modelToDto(User user) {
        return UserDto.builder()
                .createdArticles(user.getCreatedArticles().stream().map(o -> ArticleDto.builder()
                        .content(o.getContent())
                        .creator(o.getCreator().getId())
                        .roleAccess(o.getArticle().getRoleAccess())
                        .title(o.getTitle())
                        .topic(o.getArticle().getTopic())
                        .versionDate(o.getId().getVersion())
                        .build()).collect(Collectors.toSet()))
                .createdNamespaces(user.getCreatedNamespaces().stream().map(o -> NamespaceDto.builder()
                        .creator(o.getCreator().getId())
                        .id(o.getId())
                        .title(o.getTitle())
                        .build()).collect(Collectors.toSet()))
                .email(user.getEmail())
                .groups(user.getGroups().stream().map(o -> GroupDto.builder()
                        .article(o.getArticles().stream().map(a ->
                                a.getId().getArticleId()).collect(Collectors.toList()))
                        .description(o.getDescription())
                        .id(o.getId())
                        .users(o.getUsers().stream().map(User::getId).collect(Collectors.toList()))
                        .build()).collect(Collectors.toSet()))
                .id(user.getId())
                .name(user.getName())
                .Namespaces(user.getNamespaces().stream().map(n ->
                        new NamespaceDto(n.getId(), n.getTitle(), n.getCreator().getId())).collect(Collectors.toSet()))
                .role(user.getRole())
                .build();
    }
}
