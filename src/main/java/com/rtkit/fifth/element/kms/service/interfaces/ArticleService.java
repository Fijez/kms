package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.dto.ArticleAddDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.repository.ArticleSearchCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    ArticleDto addNewArticle(ArticleAddDto article);

    List<ArticleDto> searchArticle(List<ArticleSearchCriteria> searchCriteria);

    Slice<ArticleDto> searchArticles(Optional<String> creator, Optional<String> title, Optional<String> topic,
            Optional<String> content, Optional<String[]> tags, Pageable pageable);

    ArticleDto update ( ArticleUpdateDto articleUpdateDto);

    Optional<Article> findById(Long id);
}
