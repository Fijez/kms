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
    //TODO: реализовать
    public Optional<Article> findById(Long id) {
        return Optional.empty();
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
    public ArticleUpdateDto update(ArticleUpdateDto articleDto) {

        Article oldArticle = articleRepo.findById(articleDto.getId()).orElseThrow(() -> new EntityNotFoundException("entity not found"));
        //подразумевается, что указывают id существующего тега, надо поменять
        if (articleDto.getTags() != null || articleDto.getTags().get(0) != null) {
            oldArticle.getTags().addAll(articleDto.getTags().stream()
                    .map(o -> tagRepo.findById(o).orElse(null))
                    .collect(Collectors.toSet()));
        }

        //TODO:найти в полученном списке пользователей уникальных
        Set<ArticleUser> users = oldArticle.getUsers();
//        List<UserRoleDto> newUsers = articleDto.getUsers();
//        users.forEach(o -> {
//            if (o.getId().getArticleId().equals(articleDto.getId()) &&
//                    newUsers.stream().filter(n ->  {
//
//                        n.getUserId().equals(o.getId().getUserId())
//                    }))
//        });
        users.addAll(articleDto.getUsers().stream().map(u ->
                new ArticleUser(userRepo.findById(u.getUserId()).orElseThrow(),
                        articleRepo.findById(articleDto.getId()).orElseThrow(),
                        Optional.of(u.getRole()).orElse(Role.USER))).collect(Collectors.toSet()));

        Article newArticle = Article.builder()
                .id(articleDto.getId())
                .title(articleDto.getTitle())
                .creator(userRepo.findById(articleDto.getCreatorId()).orElseThrow())
                .content(Optional.of(articleDto.getContent()).orElse(oldArticle.getContent()))
                .versionDate(Optional.of(articleDto.getContent()).stream()
                        .map(content -> !content.isBlank() ? LocalDateTime.now(ZoneId.systemDefault()) : oldArticle.getVersionDate())
                        .findFirst().get())
                .topic(Optional.of(articleDto.getTopic()).orElse(oldArticle.getTopic()))
                .users(users)
                .tags(oldArticle.getTags())
                .roleAccess(Optional.of(Role.valueOf(articleDto.getRoleAccess())).orElse(Role.USER))
                .namespace(namespaceRepo.findById(articleDto.getNamespaceId()).orElseThrow(() -> new EntityNotFoundException("bad request")))
                .build();
        if (articleDto.getContent().compareTo(oldArticle.getContent()) != 0) {
            //TODO: добавление новой записи/версии в бд и изменение всех связных индексов
        }
        articleRepo.save(newArticle);
        return articleDto;
    }
}