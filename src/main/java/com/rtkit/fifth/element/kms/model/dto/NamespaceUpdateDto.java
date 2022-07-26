package com.rtkit.fifth.element.kms.model.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность, для обновления  namespace")
public class NamespaceUpdateDto {
    private Long id;
    @Schema(description = "Новое имя для namespace")
    private String title;
    @ArraySchema(arraySchema = @Schema (description = "Id новых пользователей namespace"))
    private List<Long> users;
    @ArraySchema(arraySchema = @Schema (description = "Id новых статей для namespace"))
    private List<Long> articles;
}
