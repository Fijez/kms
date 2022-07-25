package com.rtkit.fifth.element.kms.model.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDto implements Iterable<Long> {

    @NotBlank
    private Long id;
    @Schema(description = "Новое название статьи")
    private String title;
    @Schema(description = "Новая тема статьи")
    private String topic;
    @Schema(description = "Новый текст статьи")
    private String content;
    @Schema(description = "Новый создатель(модератор) статьи")
    private Long creatorId;
    @ArraySchema(arraySchema = @Schema(description = "Id новых тегов"))
    private List<Long> tags;
    @Schema(description = "Новый ролевой доступ к статье")
    private String roleAccess;
    @ArraySchema(arraySchema = @Schema(description = "Id новых пользователей"))
    private List<UserRoleDto> users;//TODO:надо проверить на наличие уже существующих пользователей
    @Schema(description = "Id для нового namespace")
    private Long namespaceId;

    @Override
    public Iterator<Long> iterator() {
        return tags.iterator();
    }

    @Override
    public void forEach(Consumer<? super Long> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Long> spliterator() {
        return Iterable.super.spliterator();
    }
}
