package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Namespace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private String title;

    private String creator;

    private String project;

    private String author;

    private String topic;

    private LocalDate versionDate;

    private String content;

    private String roleAccess;

    private Set<String> tags;

    private Set<Namespace> namespaces;
}
