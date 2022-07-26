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

    @Schema(description = "Название группы")
    private String title;

    @Schema(description = "Описание группы")
    private String description;

    @Schema(description = "Id новых статей, доступных группе")
    private List<Long> article;

    @Schema(description = "Id новых пользователей в группе")
    private List<Long> users;
}
