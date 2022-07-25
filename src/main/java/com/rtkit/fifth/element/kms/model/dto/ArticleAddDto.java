package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

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
    private LocalDate versionDate;
    @Schema(description = "Текст статьи")
    private String content;

    public ArticleAddDto(Article article) {
        this.title = article.getTitle();
        this.topic = article.getTopic();
        this.content = article.getContent();
        this.creator = article.getCreator().getEmail();
    }
}
