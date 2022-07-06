package com.rtkit.fifth.element.kms.controller.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSearchDto {
    private String author;
    private String title;
    private String phrase;
}