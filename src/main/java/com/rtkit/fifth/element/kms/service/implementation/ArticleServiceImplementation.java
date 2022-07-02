package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.repository.ArticleRepo;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImplementation implements ArticleService {

    private final ArticleRepo articleRepo;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImplementation(ArticleRepo articleRepo, ArticleMapper articleMapper) {
        this.articleRepo = articleRepo;
        this.articleMapper = articleMapper;
    }

    @Override
    @Transactional
    public void addNewArticle(Article article) {
        articleRepo.saveAndFlush(article);
    }

    @Override
    public ArticleDto searchArticle(ArticleDto searchRequest) {
        //надо использовать projectDTO
//        articleRepo.findByTitleAndAuthorAndTopicAndVersionDateAndProjectAndNamespace(searchRequest.getTitle(),
//                searchRequest.getAuthor(), searchRequest.getTopic(), searchRequest.getVersionDate(),
//                searchRequest.getProject(), searchRequest.getNamespaces());
        return articleMapper.modelToDto(null);
    }
//    public ArticleDto searchArticle(ArticleSearchRequest searchRequest) {
//        return articleMapper.modelToDto(null);
//    }
}
