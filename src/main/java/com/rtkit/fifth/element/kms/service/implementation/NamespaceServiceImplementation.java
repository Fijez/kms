package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.service.interfaces.NamespaceService;
import com.rtkit.fifth.element.kms.repository.NamespaceRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class NamespaceServiceImplementation implements NamespaceService {

    private final NamespaceRepo namespaceRepo;

    public NamespaceServiceImplementation(NamespaceRepo namespaceRepo) {
        this.namespaceRepo = namespaceRepo;
    }
}
