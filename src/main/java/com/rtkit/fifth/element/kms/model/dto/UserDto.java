package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private Role role;

    private Set<ArticleDto> createdArticles;

    private Set<NamespaceDto> createdNamespaces;

    private Set<GroupDto> groups;

    private Set<NamespaceDto> Namespaces;
}
