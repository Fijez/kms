package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.controller.util.ArticleSearchRequest;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.repository.ArticleSearchCriteria;

import java.util.List;

public interface ArticleService {

    void addNewArticle(Article article);

    List <ArticleDto> searchArticle(ArticleSearchRequest searchRequest);

    List <ArticleDto> searchArticle(List<ArticleSearchCriteria> searchCriteria);
}
