package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDto {

    @NotBlank
    private long id;
    private String title;
    private String author;
    private String topic;
    private String content;
    private String creator;

    public ArticleUpdateDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.author = article.getAuthor();
        this.topic = article.getTopic();
        this.content = article.getContent();
        this.creator = article.getCreator().getEmail();
    }
}
