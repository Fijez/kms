package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.controller.util.ArticleSearchRequest;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;

public interface ArticleService {
    void addNewArticle(ArticleDto article);

    ArticleUpdateDto update ( ArticleUpdateDto articleUpdateDto);
}
