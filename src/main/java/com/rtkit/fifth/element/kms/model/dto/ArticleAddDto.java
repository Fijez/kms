package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAddDto {

    @Schema(description = "Название статьи")
    private String title;

    @Schema(description = "Создатель (модератор) статьи")
    private String creator;

    @Schema(description = "Тема статьи")
    private String topic;

    @Schema(description = "Версия статьи")
    private LocalDateTime versionDate;

    @Schema(description = "Текст статьи")
    private String content;

    private Long namespace;

    public ArticleAddDto(Article article) {
        this.title = article.getVersions().last().getTitle();
        this.namespace = article.getNamespace().getId();
        this.topic = article.getTopic();
        this.content = article.getVersions().last().getContent();
        this.creator = article.getVersions().last().getCreator().getEmail();
    }
}
