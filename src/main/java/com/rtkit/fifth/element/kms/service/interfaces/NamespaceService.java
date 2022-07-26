package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.dto.NamespaceDto;
import com.rtkit.fifth.element.kms.model.dto.NamespaceUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Namespace;

public interface NamespaceService {
    NamespaceDto addNewNamespace(NamespaceDto namespaceDto);

    Namespace addNewNamespace(Namespace namespaceDto);

    Namespace findByTitle(String title);

    NamespaceUpdateDto update(NamespaceUpdateDto namespaceDto);
}

