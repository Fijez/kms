package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.NamespaceDto;
import com.rtkit.fifth.element.kms.model.entity.Namespace;
import com.rtkit.fifth.element.kms.model.mapper.NamespaceMapper;
import com.rtkit.fifth.element.kms.repository.NamespaceRepo;
import com.rtkit.fifth.element.kms.repository.UserRepo;
import com.rtkit.fifth.element.kms.service.interfaces.NamespaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class NamespaceServiceImplementation implements NamespaceService {

    private final NamespaceRepo namespaceRepo;
    private final UserRepo userRepo;
    private final NamespaceMapper namespaceMapper;

    @Autowired
    public NamespaceServiceImplementation(NamespaceRepo namespaceRepo, UserRepo userRepo, NamespaceMapper namespaceMapper) {
        this.namespaceRepo = namespaceRepo;
        this.userRepo = userRepo;
        this.namespaceMapper = namespaceMapper;
    }

    @Override
    @Transactional
    public NamespaceDto addNewNamespace(NamespaceDto namespaceDto) {
        Namespace namespace = Namespace.builder()
                .creator(userRepo.findByEmail(namespaceDto.getCreator()))
                .title(namespaceDto.getTitle())
                .users(null)
                .articles(null)
                .build();
        namespaceRepo.save(namespace);
        return namespaceMapper.modelToDto(namespace);
    }

    @Override
    //TODO: возвращать DTO
    public Namespace getByTitle(String title) {
        return namespaceRepo.getByTitle(title);
    }
}
