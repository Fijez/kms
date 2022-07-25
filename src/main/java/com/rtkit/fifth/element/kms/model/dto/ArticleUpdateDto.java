package com.rtkit.fifth.element.kms.model.dto;

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
    private String title;
    private String topic;
    private String content;
    private Long creatorId;
    private List<Long> tags;
    private String roleAccess;
    private List<UserRoleDto> users;//TODO:надо проверить на наличие уже существующих пользователей
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
