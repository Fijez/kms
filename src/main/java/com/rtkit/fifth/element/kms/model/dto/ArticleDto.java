package com.rtkit.fifth.element.kms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    @NotBlank
    private int id;
    private String title;
    private String author;
    private String topic;
    private String content;
    private String creator;
}
