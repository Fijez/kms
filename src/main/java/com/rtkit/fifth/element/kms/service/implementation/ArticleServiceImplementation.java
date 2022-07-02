package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.controller.util.ArticleSearchRequest;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.repository.ArticleRepo;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import com.rtkit.fifth.element.kms.service.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImplementation implements ArticleService {

    private final ArticleRepo articleRepo;
    private final ArticleMapper articleMapper;
    private final ProjectService projectService;

    @Autowired
    public ArticleServiceImplementation(ArticleRepo articleRepo,
            ArticleMapper articleMapper, ProjectService projectService) {
        this.articleRepo = articleRepo;
        this.articleMapper = articleMapper;
        this.projectService = projectService;
    }

    @Override
    @Transactional
    public void addNewArticle(Article article) {
        articleRepo.saveAndFlush(article);
    }

    @Override
    public List<ArticleDto> searchArticle(ArticleSearchRequest searchRequest) {
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> articles = articleRepo.findByTitleAndAuthorAndTopicAndVersionDateAndProjectAndNamespace(
                searchRequest.getTitle(),
                searchRequest.getAuthor(),
                searchRequest.getTopic(),
                searchRequest.getVersionDate(),
                projectService.getProject(searchRequest.getProject()),
                searchRequest.getNamespaces());

        for (Article a : articles) {
            articleDtos.add(articleMapper.modelToDto(a));
        }

        return articleDtos;
    }
}
