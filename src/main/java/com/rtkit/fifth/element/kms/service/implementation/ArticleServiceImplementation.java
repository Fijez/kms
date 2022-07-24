package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.ArticleAddDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.Role;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.repository.ArticleRepo;
import com.rtkit.fifth.element.kms.repository.ArticleSpec;
import com.rtkit.fifth.element.kms.repository.UserRepo;
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
    public Optional<Article> findById(Long id){
        return articleRepo.findById(id);
    }

    @Override
    public Slice<ArticleDto> searchArticles(Optional<String> creator, Optional<String> title, Optional<String> topic,
            Optional<String> content, Optional<String[]> tags, Pageable pageable) {

        ArticleSpec articleSpec = new ArticleSpec(creator, title, topic, content, tags);
        Slice<Article> articles = articleRepo.findAll(articleSpec, pageable);
        return articles.map(articleMapper::modelToDto);
    }

    @Override
    @Transactional
    public ArticleDto update ( ArticleUpdateDto articleUpdateDto) {
        var article = articleRepo.findById(articleUpdateDto.getId()).orElseThrow(() -> new EntityNotFoundException("entity not found"));
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
        return articleMapper.modelToDto(article);
    }
}
