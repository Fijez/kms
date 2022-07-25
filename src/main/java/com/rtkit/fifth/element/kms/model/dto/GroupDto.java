package com.rtkit.fifth.element.kms.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private Long id;
    @Schema(description = "�������� ������")
    private String title;
    @Schema(description = "�������� ������")
    private String description;
    @Schema(description = "Id ����� ������, ��������� ������")
    private List<Long> article;
    @Schema(description = "Id ����� ������������� � ������")
    private List<Long> users;
}
