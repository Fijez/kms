package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.ArticleSearchDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.repository.ArticleRepo;
import com.rtkit.fifth.element.kms.repository.ArticleSearchCriteria;
import com.rtkit.fifth.element.kms.repository.ArticleSpecification;
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
    public List<ArticleDto> searchArticle(List<ArticleSearchCriteria> searchCriteria) {
        ArticleSpecification appleSpecification = new ArticleSpecification();
        searchCriteria.stream().map(searchCriterion -> new ArticleSearchCriteria(searchCriterion.getKey(),
                searchCriterion.getValue(), searchCriterion.getOperation())).forEach(appleSpecification::add);

        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> articles = articleRepo.findAll(appleSpecification);

        for (Article a : articles) {
            articleDtos.add(articleMapper.modelToDto(a));
        }

        return articleDtos;
    }

    @Override
    public List<ArticleDto> searchArticle(ArticleSearchDto searchRequest) {
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
