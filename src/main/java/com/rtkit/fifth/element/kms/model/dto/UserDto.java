package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
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
