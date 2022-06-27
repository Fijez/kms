package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.controller.util.ArticleSearchRequest;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.entity.Article;

public interface ArticleService {
    void addNewArticle(Article article);

    ArticleDto searchArticle(ArticleSearchRequest searchRequest);
}
