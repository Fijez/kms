package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.NamespaceDto;
import com.rtkit.fifth.element.kms.model.dto.NamespaceUpdateDto;
import com.rtkit.fifth.element.kms.service.interfaces.NamespaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Namespace controller", description = "Создание и редактирование namespace")
@RequestMapping("/namespace")
public class NamespaceController {

    private final NamespaceService namespaceService;

    @Autowired
    public NamespaceController(NamespaceService namespaceService) {
        this.namespaceService = namespaceService;
    }

    //TODO: открыть доступ только администратору
    @PostMapping
    @Operation(summary = "Создание нового namespace")
    public NamespaceDto addNewNamespace(@RequestBody NamespaceDto namespaceDto) {
        return namespaceService.addNewNamespace(namespaceDto);
    }

    @PutMapping
    @Operation(summary = "Обновление уже существующего namespace")
    public NamespaceUpdateDto update(@RequestBody NamespaceUpdateDto namespaceUpdateDto) {
        return namespaceService.update(namespaceUpdateDto);
    }
}

