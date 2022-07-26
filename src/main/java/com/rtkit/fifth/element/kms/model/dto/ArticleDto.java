package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ArticleDto {

    private Long id;
    @Schema(description = "Название статьи")
    private String title;
    @Schema(description = "Автор(модератор) статьи")
    private String creator;
    @Schema(description = "Тема статьи")
    private String topic;
    @Schema(description = "Версия статьи")
    private LocalDate versionDate;
    @Schema(description = "Текст статьи")
    private String content;
    @Schema(description = "Доступ к статье")
    private Role roleAccess;

//    private Set<Tag> tags;
//
//    private NamespaceDto namespaces;
//
//    private Set<UserDto> users;
//
//    private List<GroupDto> groups;

}
