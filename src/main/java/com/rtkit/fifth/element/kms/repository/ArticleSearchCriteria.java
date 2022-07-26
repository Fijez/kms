package com.rtkit.fifth.element.kms.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleSearchCriteria {

    private String key;
    private Object value;
    private ArticleSearchOperation operation;
}
