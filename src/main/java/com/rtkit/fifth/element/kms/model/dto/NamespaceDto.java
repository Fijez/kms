package com.rtkit.fifth.element.kms.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность, для создания  namespace")
public class NamespaceDto {

    private Long id;

    @Schema(description = "Название namespace")
    private String title;

    @Schema(description = "Создатель namespace, он же 0 администратор")
    private String creator;
}
