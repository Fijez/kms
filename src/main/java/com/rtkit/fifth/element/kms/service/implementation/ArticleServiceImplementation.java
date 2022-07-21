package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.ArticleAddDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.*;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.repository.*;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import com.rtkit.fifth.element.kms.service.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImplementation implements ArticleService {

    private final ArticleRepo articleRepo;
    private final ArticleMapper articleMapper;
    private final GroupService groupService;
    private final UserRepo userRepo;
    private final ArticleUserRepo articleUserRepo;
    private final TagRepo tagRepo;
    private final NamespaceRepo namespaceRepo;

    @Autowired
    public ArticleServiceImplementation(ArticleRepo articleRepo
            , ArticleMapper articleMapper
            , GroupService groupService
            , UserRepo userRepo, ArticleUserRepo articleUserRepo, TagRepo tagRepo, NamespaceRepo namespaceRepo) {
        this.articleRepo = articleRepo;
        this.articleMapper = articleMapper;
        this.groupService = groupService;
        this.userRepo = userRepo;
        this.articleUserRepo = articleUserRepo;
        this.tagRepo = tagRepo;
        this.namespaceRepo = namespaceRepo;
    }

    //TODO: реализовать добавление полей которые сейчас null, или убрать их
    // путем создания ArticleAddDto, или другим способом
    @Override
    @Transactional
    public ArticleDto addNewArticle(ArticleAddDto articleAddDto) {
        Article article = Article.builder()
                .groups(null)
                .users(null)
                .namespace(null)
                .tags(null)
                .content(articleAddDto.getContent())
                .title(articleAddDto.getTitle())
                .topic(articleAddDto.getTopic())
                .versionDate(LocalDateTime.now(ZoneId.systemDefault()))
                .creator(userRepo.findByEmail(articleAddDto.getCreator()))
                .roleAccess(Role.USER)
                .build();
        articleRepo.save(article);
        return articleMapper.modelToDto(article);
    }


    @Override
    public Slice<ArticleDto> searchArticles(Optional<String> creator, Optional<String> title, Optional<String> topic,
            Optional<String> content, Optional<String[]> tags, Pageable pageable) {

        ArticleSpec articleSpec = new ArticleSpec(creator, title, topic, content, tags);
        Slice<Article> articles = articleRepo.findAll(articleSpec, pageable);
        return articles.map(articleMapper::modelToDto);
    }

    @Override
    public List<ArticleDto> searchArticle(List<ArticleSearchCriteria> searchCriteria) {

        ArticleSpecification articleSpecification = new ArticleSpecification();
        articleSpecification.add(searchCriteria);
        List<Article> articles = articleRepo.findAll(articleSpecification);
        List<ArticleDto> articleDtos = articleMapper.modelToDto(articles);

        return articleDtos;
    }



    @Override
    @Transactional
    public ArticleUpdateDto update ( ArticleUpdateDto articleDto) {
        var article = articleRepo.findById(articleDto.getId()).orElseThrow(() -> new EntityNotFoundException("entity not found"));
        article.setTitle(articleDto.getTitle());
        article.setCreator(userRepo.findByEmail(articleDto.getCreator()));
        if (articleDto.getContent() != null) {
            article.setContent(articleDto.getContent());
            article.setVersionDate(LocalDateTime.now(ZoneId.systemDefault()));
        }
        if (articleDto.getTopic() != null) {
            article.setTopic(articleDto.getTopic());
        }
        Set<ArticleUser> users = article.getUsers();
        articleDto.getUsers().forEach(x -> users.add(new ArticleUser(new ArticleUserId(article.getId(),x),Role.USER, article,userRepo.findById(x).orElseThrow(()-> new EntityNotFoundException("bad request")))));
        articleDto.getUsers().forEach(x -> articleUserRepo.save((new ArticleUser(new ArticleUserId(article.getId(),x),Role.USER, article,userRepo.findById(x).orElseThrow(()-> new EntityNotFoundException("bad request"))))));
        article.setUsers(users);
        Set<Tag> tags = article.getTags();
        articleDto.getTags().forEach(x-> tags.add(tagRepo.findByTitle(x)));
        article.setTags(tags);
        article.setRoleAccess(Role.valueOf(articleDto.getRoleAccess()));
        if (articleDto.getNamespaceId() != null )
        article.setNamespace(namespaceRepo.findById(articleDto.getNamespaceId()).orElseThrow(()-> new EntityNotFoundException("bad request")));
        articleRepo.save(article);
        return articleDto;
    }
}
