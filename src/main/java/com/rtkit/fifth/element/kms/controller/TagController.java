package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.TagDto;
import com.rtkit.fifth.element.kms.service.interfaces.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="Tag controller", description="Создание тегов")
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    @Operation(summary = "Создание новых тегов")
    public String addNewTag(@RequestBody TagDto tagDto) {
        return tagService.addNewTag(tagDto);
    }
}
