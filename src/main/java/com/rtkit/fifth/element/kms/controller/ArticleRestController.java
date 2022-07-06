package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.controller.util.ArticleSearchDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleRestController {

    private final ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewArticle(@RequestBody @Validated Article article) {
        articleService.addNewArticle(article);
    }

    @GetMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Article getArticle(@RequestBody ArticleSearchDto searchRequest) {
        return null;
    }
}
