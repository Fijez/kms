package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    private ArticleService articleService;

    @GetMapping("/search")
    public String search(ArticleDto article) {
        articleService.searchArticle(article);
        return "search";
    }

}
