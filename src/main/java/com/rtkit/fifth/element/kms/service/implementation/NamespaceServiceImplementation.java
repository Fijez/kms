package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.NamespaceDto;
import com.rtkit.fifth.element.kms.model.dto.NamespaceUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.Namespace;
import com.rtkit.fifth.element.kms.model.entity.User;
import com.rtkit.fifth.element.kms.model.mapper.NamespaceMapper;
import com.rtkit.fifth.element.kms.repository.ArticleRepo;
import com.rtkit.fifth.element.kms.repository.NamespaceRepo;
import com.rtkit.fifth.element.kms.repository.UserRepo;
import com.rtkit.fifth.element.kms.service.interfaces.NamespaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class NamespaceServiceImplementation implements NamespaceService {

    private final NamespaceRepo namespaceRepo;
    private final UserRepo userRepo;
    private final NamespaceMapper namespaceMapper;
    private final ArticleRepo articleRepo;

    @Autowired
    public NamespaceServiceImplementation(NamespaceRepo namespaceRepo,
                                          UserRepo userRepo,
                                          NamespaceMapper namespaceMapper,
                                          ArticleRepo articleRepo) {
        this.namespaceRepo = namespaceRepo;
        this.userRepo = userRepo;
        this.namespaceMapper = namespaceMapper;
        this.articleRepo = articleRepo;
    }

    @Override
    @Transactional
    public NamespaceDto addNewNamespace(NamespaceDto namespaceDto) {
        Namespace namespace = Namespace.builder()
                .creator(userRepo.findById(namespaceDto.getCreator()).orElseThrow())
                .title(namespaceDto.getTitle())
                .users(null)
                .articles(null)
                .build();
        namespaceRepo.save(namespace);
        return namespaceMapper.modelToDto(namespace);
    }

    @Override
    @Transactional
    public Namespace addNewNamespace(Namespace namespace) {
        return namespaceRepo.save(namespace);
    }

    @Override
    public Namespace findByTitle(String title) {
        return namespaceRepo.findByTitle(title);
    }

    @Override
    @Transactional
    public NamespaceUpdateDto update(NamespaceUpdateDto namespaceDto) {
        Namespace namespace = namespaceRepo.findById(namespaceDto.getId()).orElseThrow(() -> new EntityNotFoundException("entity not found"));
        namespace.setTitle(Optional.of(namespaceDto.getTitle()).orElse(namespace.getTitle()));
        Set<Article> articles = namespace.getArticles();
        //TODO: проверить на уже существующие в бд статьи
        namespaceDto.getArticles().forEach(x -> articles.add(articleRepo.findById(x).orElseThrow(() -> new EntityNotFoundException("bad request"))));
        namespace.setArticles(articles);
        Set<User> users = namespace.getUsers();
        //TODO: проверить на уже существующих в бд пользователей
        namespaceDto.getUsers().forEach(x -> users.add(userRepo.findById(x).orElseThrow(() -> new EntityNotFoundException("bad request"))));
        namespace.setUsers(users);
        namespaceRepo.save(namespace);
        return new NamespaceUpdateDto();
    }
}
