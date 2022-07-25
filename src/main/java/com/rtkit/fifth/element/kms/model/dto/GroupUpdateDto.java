package com.rtkit.fifth.element.kms.model.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность, для обновление группы")
public class GroupUpdateDto {
    private Long id;
    @Schema(description = "Новое название группы")
    private String title;
    @Schema(description = "Новое описание группы")
    private String description;
    @ArraySchema(arraySchema = @Schema (description = "Id новых статей, доступных группе"))
    private List<Long> article;
    @ArraySchema(arraySchema = @Schema (description = "Id новых пользователей для группы"))
    private List<Long> users;
}
