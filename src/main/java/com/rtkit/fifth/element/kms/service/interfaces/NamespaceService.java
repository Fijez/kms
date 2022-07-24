package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.dto.NamespaceDto;
import com.rtkit.fifth.element.kms.model.entity.Namespace;

public interface NamespaceService {
    public NamespaceDto addNewNamespace(NamespaceDto namespaceDto);

    Namespace getByTitle(String title);
}

