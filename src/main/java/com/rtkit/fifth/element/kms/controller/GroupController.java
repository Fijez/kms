package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.entity.Group;
import com.rtkit.fifth.element.kms.service.interfaces.GroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public Group groupAdd(@RequestBody GroupDto groupDto){
        return groupService.groupAdd(groupDto);
    }
    @PutMapping
    public GroupDto groupUpdate(@RequestBody GroupDto groupDto){
        return groupService.groupUpdate(groupDto);
    }
}
