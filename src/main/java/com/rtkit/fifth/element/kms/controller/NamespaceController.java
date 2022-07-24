package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.NamespaceDto;
import com.rtkit.fifth.element.kms.model.dto.NamespaceUpdateDto;
import com.rtkit.fifth.element.kms.service.interfaces.NamespaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/namespace")
public class NamespaceController {

    private final NamespaceService namespaceService;

    @Autowired
    public NamespaceController(NamespaceService namespaceService) {
        this.namespaceService = namespaceService;
    }

    @PostMapping
    public NamespaceDto addNewNamespace(@RequestBody NamespaceDto namespaceDto) {
        return namespaceService.addNewNamespace(namespaceDto);
    }

    @PutMapping
    public NamespaceUpdateDto update(@RequestBody NamespaceUpdateDto namespaceUpdateDto) {
        return namespaceService.update(namespaceUpdateDto);
    }
}

