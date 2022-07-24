package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.service.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    //TODO: добавить возможность добавлять теги только администраторам
    // статей или глобальным администраторам
    public String addNewTag(@RequestBody String name) {
        return tagService.addNewTag(name);
    }
}
