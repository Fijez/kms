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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
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
                .namespace(null)//TODO: сделать дефолтный
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
    public Optional<Article> findById(Long id) {
        return articleRepo.findById(id);
    }

    @Override
    public Slice<ArticleDto> searchArticles(Optional<String> creator, Optional<String> title, Optional<String> topic,
                                            Optional<String> content, Optional<String[]> tags, Pageable pageable, Authentication authentication) {

        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new RuntimeException("Unauthorized");
        }

        Optional<User> user = Optional.of(userRepo.findByEmail(creator.get()));

        User inquirer = userRepo.findByEmail(authentication.getName());
        Set<Namespace> namespaces = inquirer.getNamespaces();
        Set<List<ArticleGroup>> articleGroups = new HashSet<>();
        inquirer.getGroups().forEach(group -> articleGroups.add(group.getArticles().stream().collect(Collectors.toList())));

        ArticleSpec articleSpec = new ArticleSpec(user, title, topic, content, tags, namespaces, articleGroups, inquirer);
        Slice<Article> articles = articleRepo.findAll(articleSpec, pageable);
        return articles.map(articleMapper::modelToDto);
    }

    //TODO удалить метод после проверки работы ArticleSpec с новой логикой по проверке доступа

//    private Slice<Article> filterByAccess(Slice<Article> articles, Authentication authentication) {
//        if (authentication instanceof AnonymousAuthenticationToken) {
//            throw new RuntimeException("Unauthorized");
//        }
//
//        var grantedAuthorities = authentication.getAuthorities();
//        Set<String> authorities = new HashSet<>();
//        grantedAuthorities.forEach(a -> authorities.add(a.getAuthority()));
//
//        Set<Article> accessibleArticles = new HashSet<>();
//
//        articles.forEach(article -> article.getGroups().forEach(articleGroup -> {
//            if (authorities.contains(articleGroup.getGroup().getTitle().toUpperCase())) {
//                accessibleArticles.add(article);
//            }
//        }));
//
//        articles.forEach(article -> {
//            if (article.getNamespace() != null && authorities.contains(article.getNamespace().getTitle().toUpperCase())) {
//                accessibleArticles.add(article);
//            }
//        });
//
//        articles.forEach(article -> article.getUsers().forEach(articleUser -> {
//            if (authorities.contains(articleUser.getUserRole().getAuthority().toUpperCase() + "_" + articleUser.getArticle().getTitle().toUpperCase())) {
//                accessibleArticles.add(article);
//            }
//        }));
//
//        return new SliceImpl<>(new ArrayList<>(accessibleArticles), articles.getPageable(), articles.hasNext());
//    }
    @Override
    @Transactional
    public ArticleUpdateDto update(ArticleUpdateDto articleDto) {
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
        articleDto.getUsers().forEach(user -> users.add(new ArticleUser(new ArticleUserId(article.getId(), user), Role.USER, article, userRepo.findById(user).orElseThrow(() -> new EntityNotFoundException("bad request")))));
        articleDto.getUsers().forEach(user -> articleUserRepo.save((new ArticleUser(new ArticleUserId(article.getId(), user), Role.USER, article, userRepo.findById(user).orElseThrow(() -> new EntityNotFoundException("bad request"))))));
        article.setUsers(users);

        Set<Tag> tags = article.getTags();
        articleDto.getTags().forEach(tag -> tags.add(tagRepo.findById(tag).orElseThrow(() -> new EntityNotFoundException("bad request"))));
        article.setTags(tags);
        article.setRoleAccess(Role.valueOf(articleDto.getRoleAccess()));

        if (articleDto.getNamespaceId() != null)
            article.setNamespace(namespaceRepo.findById(articleDto.getNamespaceId()).orElseThrow(() -> new EntityNotFoundException("bad request")));

        articleRepo.save(article);
        return articleDto;
    }
}
