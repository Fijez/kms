package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.repository.ArticleSearchCriteria;
import com.rtkit.fifth.element.kms.service.implementation.ArticleServiceImplementation;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/article")
public class ArticleRestController {

    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleServiceImplementation articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    @Parameter(name = "creator", in = ParameterIn.QUERY, content = @Content(schema = @Schema(type = "string")), description = "Создатель")
    @Parameter(name = "title", in = ParameterIn.QUERY, content = @Content(schema = @Schema(type = "string")), description = "Заголовок")
    @Parameter(name = "topic", in = ParameterIn.QUERY, content = @Content(schema = @Schema(type = "string")), description = "Тема")
    @Parameter(name = "content", in = ParameterIn.QUERY, content = @Content(schema = @Schema(type = "string")), description = "Текст")
    @Parameter(name = "tags", in = ParameterIn.QUERY, content = @Content(schema = @Schema(type = "string[]")), description = "Тэги")
    @Operation(summary = "Поиск по статьям с фильтром")
    public ResponseEntity<Page<ArticleDto>> search(@RequestParam Optional<String> creator, @RequestParam Optional<String> title,
            @RequestParam Optional<String> topic, @RequestParam Optional<String> content, @RequestParam Optional<String[]> tags, @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(articleService.searchArticles(creator, title, topic, content, tags, pageable));
    }

    @PostMapping
    public void addNewArticle(@RequestBody ArticleDto articleDto) {
        articleService.addNewArticle(articleDto);
    }

    //    @GetMapping
    public List<ArticleDto> specification(@RequestBody List<ArticleSearchCriteria> searchCriteria) {
        return articleService.searchArticle(searchCriteria);
    }

    @PutMapping
    public ArticleUpdateDto update(@RequestBody ArticleUpdateDto articleUpdateDto) {
        return articleService.update(articleUpdateDto);
    }
}
