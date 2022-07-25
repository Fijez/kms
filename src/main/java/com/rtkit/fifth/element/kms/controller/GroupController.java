package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.dto.GroupUpdateDto;
import com.rtkit.fifth.element.kms.service.interfaces.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="Group controller", description="Создание и редактирование групп")
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    @Operation(summary = "Создание новой группы")
    public GroupDto groupAdd(@RequestBody GroupDto groupDto) {
        return groupService.groupAdd(groupDto);
    }

    @PutMapping
    @Operation(summary = "Редактирование существующей группы")
    public GroupUpdateDto groupUpdate(@RequestBody GroupUpdateDto groupDto) {
        return groupService.groupUpdate(groupDto);
    }
}
