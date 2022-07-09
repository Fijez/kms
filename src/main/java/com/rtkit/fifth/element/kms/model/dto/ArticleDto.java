package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ArticleDto {

    private Long id;
//
    private String title;

    private UserDto creator;

    private GroupDto group;

//    private String author;

    private String topic;

    private LocalDate versionDate;

    private String content;

    private Role roleAccess;

    private Set<Tag> tags;

    private Set<NamespaceDto> namespaces;

    private Set<UserDto> users;

    private List<GroupDto> groups;

}
