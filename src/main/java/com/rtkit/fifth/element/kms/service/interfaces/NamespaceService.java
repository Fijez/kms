package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.dto.NamespaceDto;
import com.rtkit.fifth.element.kms.model.dto.NamespaceUpdateDto;

public interface NamespaceService {
    NamespaceDto addNewNamespace(NamespaceDto namespaceDto);

    NamespaceUpdateDto update(NamespaceUpdateDto namespaceDto);
}

