package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.dto.GroupUpdateDto;
import com.rtkit.fifth.element.kms.model.entity.*;
import com.rtkit.fifth.element.kms.model.mapper.GroupMapper;
import com.rtkit.fifth.element.kms.repository.ArticleGroupRepo;
import com.rtkit.fifth.element.kms.repository.ArticleRepo;
import com.rtkit.fifth.element.kms.repository.GroupRepo;
import com.rtkit.fifth.element.kms.repository.UserRepo;
import com.rtkit.fifth.element.kms.service.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
public class GroupServiceImplementation implements GroupService {

    private final GroupRepo groupRepo;
    private final UserRepo userRepo;
    private final ArticleRepo articleRepo;
    private final GroupMapper groupMapper;
    private final ArticleGroupRepo articleGroupRepo;

    @Autowired
    public GroupServiceImplementation(GroupRepo groupRepo, UserRepo userRepo, ArticleRepo articleRepo, GroupMapper groupMapper, ArticleGroupRepo articleGroupRepo) {
        this.groupRepo = groupRepo;
        this.userRepo = userRepo;
        this.articleRepo = articleRepo;
        this.groupMapper = groupMapper;
        this.articleGroupRepo = articleGroupRepo;
    }

    @Override
    @Transactional
    public GroupDto groupAdd(GroupDto groupDto) {
        Group group = Group.builder()
                .title(groupDto.getTitle())
                .description(groupDto.getDescription())
                .users(null)
                .articles(null)
                .build();
        groupRepo.save(group);
        return groupDto;
    }


    @Override
    @Transactional
    public GroupUpdateDto groupUpdate(GroupUpdateDto groupDto) {
        var group = groupRepo.findById(groupDto.getId()).orElseThrow(() -> new EntityNotFoundException("entity not found"));
        group.setTitle(groupDto.getTitle());
        group.setDescription(groupDto.getDescription());
        Set<ArticleGroup> articles = group.getArticles();
        groupDto.getArticle().forEach(art -> articles.add(new ArticleGroup(new ArticleGroupId(art, group.getId()), Role.USER, articleRepo.findById(art).orElseThrow(() -> new EntityNotFoundException("bad request")), group)));
        groupDto.getArticle().forEach(art -> articleGroupRepo.save(new ArticleGroup(new ArticleGroupId(art, group.getId()), Role.USER, articleRepo.findById(art).orElseThrow(() -> new EntityNotFoundException("bad request")), group)));
        group.setArticles(articles);
        Set<User> users = group.getUsers();
        groupDto.getUsers().forEach(user -> users.add(userRepo.findById(user).orElseThrow(() -> new EntityNotFoundException("bad request"))));
        group.setUsers(users);
        groupRepo.save(group);
        return groupDto;
    }
}
