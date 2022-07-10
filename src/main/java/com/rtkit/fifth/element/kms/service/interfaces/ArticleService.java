package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.repository.ArticleSearchCriteria;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    void addNewArticle(ArticleDto article);

    List<ArticleDto> searchArticle(List<ArticleSearchCriteria> searchCriteria);

    List<ArticleDto> searchArticles(Optional<String> creator, Optional<String> title, Optional<String> topic,
            Optional<String> content, Optional<String[]> tags);

    ArticleUpdateDto update(ArticleUpdateDto articleUpdateDto);
}
