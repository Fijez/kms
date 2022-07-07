package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.Role;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.repository.ArticleRepo;
import com.rtkit.fifth.element.kms.repository.UserRepo;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImplementation implements ArticleService {

    private final ArticleRepo articleRepo;
    private final ArticleMapper articleMapper;

    private final UserRepo userRepo;

    @Autowired
    public ArticleServiceImplementation(ArticleRepo articleRepo, ArticleMapper articleMapper, UserRepo userRepo) {
        this.articleRepo = articleRepo;
        this.articleMapper = articleMapper;
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
    @Transactional
    public ArticleUpdateDto update(ArticleUpdateDto articleUpdateDto) {
        var article = findById(articleUpdateDto.getId());

        article.setTitle(articleUpdateDto.getTitle());
        article.setVersionDate(new Date());
        article.setCreator(userRepo.findByEmail(articleUpdateDto.getCreator()));

        if (articleUpdateDto.getAuthor() != null) {
            article.setAuthor(articleUpdateDto.getAuthor());
        }
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
