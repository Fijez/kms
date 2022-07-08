package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleSearchDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.Role;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.repository.ArticleRepo;
import com.rtkit.fifth.element.kms.repository.ArticleSearchCriteria;
import com.rtkit.fifth.element.kms.repository.ArticleSpecification;
import com.rtkit.fifth.element.kms.repository.UserRepo;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import com.rtkit.fifth.element.kms.service.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                .author(articleDto.getAuthor())
                .content(articleDto.getContent())
                .title(articleDto.getTitle())
                .topic(articleDto.getTopic())
                .versionDate(new Date())
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
    public List<ArticleDto> searchArticle(List<ArticleSearchCriteria> searchCriteria) {
        ArticleSpecification appleSpecification = new ArticleSpecification();
        appleSpecification.add(searchCriteria);

        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> articles = articleRepo.findAll(appleSpecification);

        for (Article a : articles) {
            articleDtos.add(articleMapper.modelToDto(a));
        }

        return articleDtos;
    }



    //TODO: разобраться, нужен ли такой метод
    @Override
    public List<ArticleDto> searchArticle(ArticleSearchDto searchRequest) {
        List<ArticleDto> articleDtos = new ArrayList<>();
//        List<Article> articles = articleRepo.findByTitleAndAuthorAndTopicAndVersionDateAndGroupAndNamespace(
//                searchRequest.getTitle(),
//                searchRequest.getAuthor(),
//                searchRequest.getTopic(),
//                searchRequest.getVersionDate(),
//                groupService.getGroup(searchRequest.getGroup()),
//                searchRequest.getNamespaces());

//        for (Article a : articles) {
//            articleDtos.add(articleMapper.modelToDto(a));
//        }

        return articleDtos;
    }
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
