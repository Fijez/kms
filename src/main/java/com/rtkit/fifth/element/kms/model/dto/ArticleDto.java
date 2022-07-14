package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.*;
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
//
    private String title;

    private String creator;

//    private String author;

    private String topic;

    private LocalDate versionDate;

    private String content;

    private Role roleAccess;

//    private Set<Tag> tags;
//
//    private NamespaceDto namespaces;
//
//    private Set<UserDto> users;
//
//    private List<GroupDto> groups;

}
