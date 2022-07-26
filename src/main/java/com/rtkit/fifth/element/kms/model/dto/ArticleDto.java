package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ArticleDto {

    private Long id;

    @Schema(description = "�������� ������")
    private String title;

    @Schema(description = "�����(���������) ������")
    private Long creator;

    @Schema(description = "���� ������")
    private String topic;

    @Schema(description = "������ ������")
    private LocalDateTime versionDate;

    @Schema(description = "����� ������")
    private String content;

    @Schema(description = "������ � ������")
    private Role roleAccess;

//    private Set<Tag> tags;
//
//    private NamespaceDto namespaces;
//
//    private Set<UserDto> users;
//
//    private List<GroupDto> groups;

}
