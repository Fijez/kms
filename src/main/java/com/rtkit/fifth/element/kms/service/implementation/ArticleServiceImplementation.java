package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.Role;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.repository.*;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import com.rtkit.fifth.element.kms.service.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImplementation implements ArticleService {

    private final ArticleRepo articleRepo;
    private final ArticleMapper articleMapper;
    private final GroupService groupService;

    private final UserRepo userRepo;

    @Autowired
    public ArticleServiceImplementation(ArticleRepo articleRepo
            , ArticleMapper articleMapper
            , GroupService groupService
            , UserRepo userRepo) {
        this.articleRepo = articleRepo;
        this.articleMapper = articleMapper;
        this.groupService = groupService;
        this.userRepo = userRepo;
    }

    //TODO: реализовать добавление полей которые сейчас null, или убрать их
    // путем создания ArticleAddDto, или другим способом
    @Override
    @Transactional
    public void addNewArticle(ArticleDto articleDto) {
        Article article = Article.builder()
                .groups(null)
                .users(null)
                .namespace(null)
                .tags(null)
                .id(articleDto.getId())
                .content(articleDto.getContent())
                .title(articleDto.getTitle())
                .topic(articleDto.getTopic())
                .versionDate(LocalDateTime.now(ZoneId.systemDefault()))
                .creator(userRepo.findByEmail(articleDto.getCreator()))
//                .creator(articleDto.getCreator())
                .roleAccess(Role.USER)
                .build();

        articleRepo.save(article);
    }

    public Article findById(Long id) {
        return articleRepo.findById(id).orElseThrow(() -> new RuntimeException("Статья не найдена"));
    }

    @Override
    public List<ArticleDto> searchArticles(Optional<String> creator, Optional<String> title, Optional<String> topic,
            Optional<String> content, Optional<String[]> tags) {

        ArticleSpec articleSpec = new ArticleSpec(creator, title, topic, content, tags);
        List<Article> articles = articleRepo.findAll(articleSpec);
        List<ArticleDto> articleDtos = articleMapper.modelToDto(articles);

        return articleDtos;
    }

    @Override
    public List<ArticleDto> searchArticle(List<ArticleSearchCriteria> searchCriteria) {

        ArticleSpecification articleSpecification = new ArticleSpecification();
        articleSpecification.add(searchCriteria);
        List<Article> articles = articleRepo.findAll(articleSpecification);
        List<ArticleDto> articleDtos = articleMapper.modelToDto(articles);

        return articleDtos;
    }


    @Transactional
    public ArticleUpdateDto update(ArticleUpdateDto articleUpdateDto) {
        var article = findById(articleUpdateDto.getId());

        article.setTitle(articleUpdateDto.getTitle());
        article.setVersionDate(LocalDateTime.now(ZoneId.systemDefault()));
        article.setCreator(userRepo.findByEmail(articleUpdateDto.getCreator()));

        if (articleUpdateDto.getContent() != null) {
            article.setContent(articleUpdateDto.getContent());
        }
        if (articleUpdateDto.getTopic() != null) {
            article.setTopic(articleUpdateDto.getTopic());
        }

        articleRepo.save(article);
        return new ArticleUpdateDto(article);
    }
}
