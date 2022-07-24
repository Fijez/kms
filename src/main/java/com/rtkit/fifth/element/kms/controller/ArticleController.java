package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.ArticleAddDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleDto;
import com.rtkit.fifth.element.kms.model.dto.ArticleUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Namespace;
import com.rtkit.fifth.element.kms.service.interfaces.ArticleService;
import com.rtkit.fifth.element.kms.service.interfaces.NamespaceService;
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

import java.util.Optional;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    private final NamespaceService namespaceService;

    @Autowired
    public ArticleController(ArticleService articleService, NamespaceService namespaceService) {
        this.articleService = articleService;
        this.namespaceService = namespaceService;
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
                                                    @PageableDefault(size = 5) Pageable pageable) {
        //TODO:убедиться, что выводятся только доступные пользователю статьи
        return ResponseEntity.ok(articleService.searchArticles(creator, title, topic, content, tags, pageable));
    }

    //TODO: добавить проверку доступа пользователя к пространству, в которое сохраняется статья
    @PostMapping
    public ArticleDto addNewArticle(@RequestBody ArticleAddDto articleAddDto, Authentication authentication) {
        String namespace = articleAddDto.getNamespace();
        String name = authentication.getName();
        Namespace byTitle = namespaceService.getByTitle(namespace);

        return articleService.addNewArticle(articleAddDto);
    }

    //TODO: проверка на доступ пользователя к статье
    @PutMapping
    public ArticleDto update(@RequestBody ArticleUpdateDto articleUpdateDto, Authentication authentication) {
        return articleService.update(articleUpdateDto);
    }
}
