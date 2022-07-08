package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleSearchDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.repository.ArticleSearchCriteria;
import com.rtkit.fifth.element.kms.service.implementation.ArticleServiceImplementation;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleRestController {

    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleServiceImplementation articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/add")
    public void addNewArticle(@RequestBody ArticleDto articleDto) {
        articleService.addNewArticle(articleDto);
    }

    @PostMapping(value = "/search")
    public List<ArticleDto> specification(@RequestBody List<ArticleSearchCriteria> searchCriteria) {
        return articleService.searchArticle(searchCriteria);
    }

    @GetMapping("/find")
    public List<ArticleDto> search(ArticleSearchDto article) {
        return articleService.searchArticle(article);
    }

    @GetMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleDto> getArticle(@RequestBody ArticleSearchDto searchRequest) {
        return articleService.searchArticle(searchRequest);
    }

    @PostMapping(value = "/update")
    public ArticleUpdateDto update(@RequestBody ArticleUpdateDto articleUpdateDto) {
        return articleService.update(articleUpdateDto);
    }
}
