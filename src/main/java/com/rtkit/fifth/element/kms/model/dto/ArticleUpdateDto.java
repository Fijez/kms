package com.rtkit.fifth.element.kms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDto {

    @NotBlank
    private Long id;
    private String title;
    private String topic;
    private String content;
    private Long creatorId;
    private List<Long> tags;
    private String roleAccess;
    private List<UserRoleDto> users;//TODO:надо проверить на наличие уже существующих пользователей
    private Long namespaceId;

}
