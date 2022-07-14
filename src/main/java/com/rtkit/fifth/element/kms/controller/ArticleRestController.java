package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.ArticleAddDto;
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

    @PostMapping
    public ArticleDto addNewArticle(@RequestBody ArticleAddDto articleAddDto) {
        return articleService.addNewArticle(articleAddDto);
    }

    @GetMapping
    public List<ArticleDto> specification(@RequestBody List<ArticleSearchCriteria> searchCriteria) {
        return articleService.searchArticle(searchCriteria);
    }

    @PutMapping
    public ArticleDto update(@RequestBody ArticleUpdateDto articleUpdateDto) {
        return articleService.update(articleUpdateDto);
    }
}
