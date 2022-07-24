package com.rtkit.fifth.element.kms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NamespaceUpdateDto {
    private Long id;

    private String title;

    private List<Long> users;

    private List<Long> articles;
}
