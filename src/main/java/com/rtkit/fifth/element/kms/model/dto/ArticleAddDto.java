package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Article;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAddDto {
    private String title;
    private String creator;
    private String topic;

    private LocalDate versionDate;
    private String content;

    public ArticleAddDto(Article article) {
        this.title = article.getTitle();
        this.topic = article.getTopic();
        this.content = article.getContent();
        this.creator = article.getCreator().getEmail();
    }
}
