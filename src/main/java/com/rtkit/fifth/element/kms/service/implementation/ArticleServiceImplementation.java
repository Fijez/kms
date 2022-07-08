package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.Role;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.repository.ArticleRepo;
import com.rtkit.fifth.element.kms.repository.UserRepo;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import com.rtkit.fifth.element.kms.service.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImplementation implements ArticleService {

    private final ArticleRepo articleRepo;
    private final ArticleMapper articleMapper;
    private final ProjectService projectService;

    private final UserRepo userRepo;

    @Autowired
    public ArticleServiceImplementation(ArticleRepo articleRepo
            , ArticleMapper articleMapper
            , ProjectService projectService
            , UserRepo userRepo) {
        this.articleRepo = articleRepo;
        this.articleMapper = articleMapper;
        this.projectService = projectService;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public void addNewArticle(ArticleDto articleDto) {

        Article article = Article.builder()
                .groups(null)
                .users(null)
                .namespace(null)
                .tags(null)
                .id(articleDto.getId())
                .author(articleDto.getAuthor())
                .content(articleDto.getContent())
                .title(articleDto.getTitle())
                .topic(articleDto.getTopic())
                .versionDate(new Date())
                .creator(userRepo.findByEmail(articleDto.getCreator()))
                .roleAccess(Role.USER)
                .build();

        articleRepo.save(article);
    }

    public Article findById(Long id) {
        return articleRepo.findById(id).orElseThrow(() -> new RuntimeException("Статья не найдена"));
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
    @Transactional
    public ArticleUpdateDto update(ArticleUpdateDto articleUpdateDto) {
        long id = articleUpdateDto.getId();
        var article = findById(id);
        if (articleUpdateDto.getAuthor() != null)
            article.setAuthor(articleUpdateDto.getAuthor());
        if (articleUpdateDto.getContent() != null)
            article.setContent(articleUpdateDto.getContent());
        article.setTitle(articleUpdateDto.getTitle());
        if (articleUpdateDto.getTopic() != null)
            article.setTopic(articleUpdateDto.getTopic());
        article.setVersionDate(new Date());
        article.setCreator(userRepo.findByEmail(articleUpdateDto.getCreator()));
        articleRepo.save(article);
        return new ArticleUpdateDto(article);
    }
}
