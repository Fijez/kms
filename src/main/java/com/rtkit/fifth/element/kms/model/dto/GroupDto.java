package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.ArticleGroup;
import com.rtkit.fifth.element.kms.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class GroupDto {

    private Long id;

    private String title;

    private String description;

    private Set<ArticleDto> article;

    private Set<UserDto> users;
}
