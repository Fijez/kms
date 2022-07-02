package com.rtkit.fifth.element.kms.controller.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//есть ли необходимость в этом классе, если в dto для article будут те же поля?
public class ArticleSearchRequest {
    private String author;
    private String title;
    private String phrase;
}
