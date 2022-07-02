package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Namespace;
import com.rtkit.fifth.element.kms.model.entity.Project;
import com.rtkit.fifth.element.kms.model.entity.Role;
import com.rtkit.fifth.element.kms.model.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private String title;

    private User creator;

    private Project project;

    private String author;

    private String topic;

    private LocalDate versionDate;

    private String content;

    private Role roleAccess;

    private Set<String> tags;

    private Set<Namespace> namespaces;
}
