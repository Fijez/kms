package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.ArticleSearchDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SearchController {

    private ArticleService articleService;

    @Autowired
    public SearchController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/search")
    public List<ArticleDto> search(ArticleSearchDto article) {
        return articleService.searchArticle(article);
    }

}
