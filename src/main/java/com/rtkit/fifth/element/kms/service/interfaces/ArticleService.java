package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.dto.ArticleAddDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface ArticleService {

    ArticleDto addNewArticle(ArticleAddDto article);

//    List<ArticleDto> searchArticle(List<ArticleSearchCriteria> searchCriteria);

    Slice<ArticleDto> searchArticles(Optional<String> creator, Optional<String> title, Optional<String> topic,
                                     Optional<String> content, Optional<String[]> tags, Pageable pageable,
                                     Authentication authentication);

    ArticleUpdateDto update(ArticleUpdateDto articleUpdateDto);

    Optional<Article> findById(Long id);
}
