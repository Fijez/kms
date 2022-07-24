package com.rtkit.fifth.element.kms.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupUpdateDto {
    private Long id;

    private String title;

    private String description;

    private List<Long> article;

    private List<Long> users;
}
