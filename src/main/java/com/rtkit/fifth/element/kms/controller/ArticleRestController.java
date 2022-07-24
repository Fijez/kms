package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.ArticleAddDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.service.interfaces.AccessChecker;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/article")
public class ArticleRestController {

    private final ArticleService articleService;
    private final ArticleMapper articleMapper;
    private final AccessChecker accessChecker;

    @Autowired
    public ArticleRestController(ArticleService articleService, ArticleMapper articleMapper, AccessChecker accessChecker) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
        this.accessChecker = accessChecker;
    }

    @GetMapping
    @Parameter(name = "creator", in = ParameterIn.QUERY, content = @Content(schema = @Schema(type = "string")), description = "Создатель")
    @Parameter(name = "title", in = ParameterIn.QUERY, content = @Content(schema = @Schema(type = "string")), description = "Заголовок")
    @Parameter(name = "topic", in = ParameterIn.QUERY, content = @Content(schema = @Schema(type = "string")), description = "Тема")
    @Parameter(name = "content", in = ParameterIn.QUERY, content = @Content(schema = @Schema(type = "string")), description = "Текст")
    @Parameter(name = "tags", in = ParameterIn.QUERY, content = @Content(schema = @Schema(type = "string[]")), description = "Тэги")
    @Operation(summary = "Поиск по статьям с фильтром")
    public ResponseEntity<Slice<ArticleDto>> search(@RequestParam Optional<String> creator,
                                                    @RequestParam Optional<String> title,
                                                    @RequestParam Optional<String> topic,
                                                    @RequestParam Optional<String> content,
                                                    @RequestParam Optional<String[]> tags,
                                                    @PageableDefault(size = 5) Pageable pageable,
                                                    Authentication authentication) {
        return ResponseEntity.ok(articleService.searchArticles(creator, title, topic, content, tags, pageable, authentication));
    }

    @GetMapping(value = "/{id}")
    @Parameter(name = "id", in = ParameterIn.QUERY, content = @Content(schema = @Schema(type = "long")), description = "Id статьи")
    public ModelAndView read(@PathVariable Long id, Authentication authentication) {
        Optional<Article> optionalArticle = articleService.findById(id);
        Article article = null;
        ArticleDto articleDto = null;

        if (optionalArticle.isPresent()) {
            article = optionalArticle.get();
            accessChecker.checkArticleAccess(article, authentication);
            articleDto = articleMapper.modelToDto(article);
        } else {
            throw new RuntimeException("Article not found");
        }

        ModelAndView mav = new ModelAndView("article");
        mav.addObject("author", articleDto.getCreator());
        mav.addObject("title", articleDto.getTitle());
        mav.addObject("topic", articleDto.getTopic());
        mav.addObject("date", articleDto.getVersionDate().toString());
        mav.addObject("content", articleDto.getContent());
        return mav;
    }

    @PostMapping
    public ArticleDto addNewArticle(@RequestBody ArticleAddDto articleAddDto) {
        return articleService.addNewArticle(articleAddDto);
    }

    @PutMapping
    public ArticleDto update(@RequestBody ArticleUpdateDto articleUpdateDto) {
        //
        return articleService.update(articleUpdateDto);
    }
}
