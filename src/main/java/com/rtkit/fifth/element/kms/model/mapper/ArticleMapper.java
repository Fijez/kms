package com.rtkit.fifth.element.kms.model.mapper;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.entity.Article;

import java.util.List;
import java.util.stream.Collectors;

//@Mapper(componentModel = "spring")
public interface ArticleMapper extends EntityMapper<ArticleDto, Article> {

    //    @Mapping(source = "article.creator.name", target = "creator")
    default ArticleDto modelToDto(Article article) {
        return ArticleDto.builder()
                .content(article.getVersions().last().getContent())
                .creator(article.getVersions().last().getCreator().getId())
                .id(article.getId())
                .roleAccess(article.getRoleAccess())
                .title(article.getVersions().last().getTitle())
                .topic(article.getTopic())
                .versionDate(article.getVersions().last().getId().getVersion())
                .build();
    }

    //    @Mapping(source = "article.creator.name", target = "creator")
    default List<ArticleDto> modelToDto(List<Article> articles) {
        return articles.stream().map(this::modelToDto).collect(Collectors.toList());
    }

}