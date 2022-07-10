package com.rtkit.fifth.element.kms.model.mapper;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper extends EntityMapper<ArticleDto, Article> {

    @Mapping(target = "creator.name")
    ArticleDto modelToDto(Article article);

    @Mapping(target = "creator.name")
    List<ArticleDto> modelToDto(List<Article> articles);
}
