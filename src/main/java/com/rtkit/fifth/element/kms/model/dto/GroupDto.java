package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.ArticleGroup;
import com.rtkit.fifth.element.kms.model.entity.User;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private Long id;

    private String title;

    private String description;

    private List<Long> article;

    private List<Long> users;
}
