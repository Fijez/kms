package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.ArticleSearchDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.Role;
import com.rtkit.fifth.element.kms.model.entity.User;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.repository.ArticleRepo;
import com.rtkit.fifth.element.kms.repository.UserRepo;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
        Article article = new Article();
        article.setGroups(null);
        article.setUsers(null);
        article.setNamespace(null);
        article.setTags(null);
        article.setId(articleDto.getId());
        article.setAuthor(articleDto.getAuthor());
        article.setContent(articleDto.getContent());
        article.setTitle(articleDto.getTitle());
        article.setTopic(articleDto.getTopic());
        article.setVersionDate(new Date());
        article.setCreator(userRepo.findByEmail(articleDto.getCreator()));
        article.setRoleAccess(Role.USER);
        articleRepo.save(article);
    }

    public Article findById (Long id)
    {
        return articleRepo.findById(id).orElseThrow(() -> new RuntimeException("Статья не найдена"));
    }

    @Override
    @Transactional
    public ArticleUpdateDto update( ArticleUpdateDto articleUpdateDto) {
        long id =articleUpdateDto.getId();
        var article = findById(id);
        if(articleUpdateDto.getAuthor() != null)
        article.setAuthor(articleUpdateDto.getAuthor());
        if(articleUpdateDto.getContent() != null)
        article.setContent(articleUpdateDto.getContent());
        article.setTitle(articleUpdateDto.getTitle());
        if(articleUpdateDto.getTopic() != null)
        article.setTopic(articleUpdateDto.getTopic());
        article.setVersionDate(new Date());
        article.setCreator(userRepo.findByEmail(articleUpdateDto.getCreator()));
        articleRepo.save(article);
        return new ArticleUpdateDto(article);
    }
}
