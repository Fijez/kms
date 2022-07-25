package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.ArticleAddDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.mapper.ArticleMapper;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Article controller", description="Создание, редактирование и поиск статей")
public class ArticleRestController {

    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleRestController(ArticleService articleService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
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
    public ModelAndView read(@PathVariable Long id) {
        Optional<Article> optionalArticle = articleService.findById(id);
        ArticleDto articleDto = null;
        if (optionalArticle.isPresent()) {
            articleDto = articleMapper.modelToDto(articleService.findById(id).get());
        } else {
            throw new RuntimeException("Article not found");
        }
        ModelAndView mav = new ModelAndView("article");
        System.out.println(articleDto.getCreator());
        System.out.println(articleDto.getTitle());
        System.out.println(articleDto.getTopic());
        System.out.println(articleDto.getVersionDate().toString());
        System.out.println(articleDto.getContent());

        mav.addObject("author", articleDto.getCreator());
        mav.addObject("title", articleDto.getTitle());
        mav.addObject("topic", articleDto.getTopic());
        mav.addObject("date", articleDto.getVersionDate().toString());
        mav.addObject("content", articleDto.getContent());
        return mav;
    }

    @PostMapping
    @Operation(summary = "Добавление новой статьями")
    public ArticleDto addNewArticle(@RequestBody ArticleAddDto articleAddDto) {
        return articleService.addNewArticle(articleAddDto);
    }

    @PutMapping
    @Operation(summary = "Редактирование существующей статьями")
    public ArticleUpdateDto update(@RequestBody ArticleUpdateDto articleUpdateDto) {
        ArticleUpdateDto update = null;
        update = articleService.update(articleUpdateDto);
        return update;
    }
}
