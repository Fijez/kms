package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Namespace;
import com.rtkit.fifth.element.kms.model.entity.Group;
import com.rtkit.fifth.element.kms.model.entity.Role;
import com.rtkit.fifth.element.kms.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//TODO: разобраться с видом DTO
public class ArticleDto {

    @NotBlank
    private int id;
//
    private String title;
//
//    private User creator;
//
//    private Group group;
//
//    private String author;
//
//    private String topic;
//
//    private LocalDate versionDate;
//
//    private String content;
//
//    private Role roleAccess;
//
//    private Set<String> tags;
//
//    private Set<Namespace> namespaces;
    private String author;
    private String topic;
    private String content;
    private String creator;
}
