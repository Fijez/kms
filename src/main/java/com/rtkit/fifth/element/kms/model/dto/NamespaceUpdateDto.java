package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NamespaceUpdateDto {
    private Long id;

    private String title;

    private List<Long> users;

    private List<Long>articles;
}
