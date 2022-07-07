package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.ArticleSearchDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.service.implementation.ArticleServiceImplementation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleRestController {

    private final ArticleServiceImplementation articleService;

    public ArticleRestController(ArticleServiceImplementation articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/add")
    public void addNewArticle(@RequestBody ArticleDto articleDto) {
        articleService.addNewArticle(articleDto);
    }

    @GetMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Article getArticle(@RequestBody ArticleSearchDto searchRequest) {
        return null;
    }

    @PostMapping(value = "/update")
    public ArticleUpdateDto update(@RequestBody ArticleUpdateDto articleUpdateDto)
    {
        return articleService.update(articleUpdateDto);
    }
}
