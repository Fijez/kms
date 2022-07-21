package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.Group;
import com.rtkit.fifth.element.kms.model.entity.User;
import com.rtkit.fifth.element.kms.model.mapper.GroupMapper;
import com.rtkit.fifth.element.kms.repository.ArticleRepo;
import com.rtkit.fifth.element.kms.repository.GroupRepo;
import com.rtkit.fifth.element.kms.repository.UserRepo;
import com.rtkit.fifth.element.kms.service.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class GroupServiceImplementation implements GroupService {

    private final GroupRepo groupRepo;
    private final UserRepo userRepo;
    private final ArticleRepo articleRepo;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupServiceImplementation(GroupRepo groupRepo, UserRepo userRepo, ArticleRepo articleRepo, GroupMapper groupMapper) {
        this.groupRepo = groupRepo;
        this.userRepo = userRepo;
        this.articleRepo = articleRepo;
        this.groupMapper = groupMapper;
    }


    @Override
    @Transactional
    public Group groupAdd(GroupDto groupDto) {
        Group group = Group.builder()
                .title(groupDto.getTitle())
                .description(groupDto.getDescription())
                .users(null)
                .articles(null)
                .build();
        groupRepo.save(group);
        return group;
    }

    @Override
    @Transactional
    public GroupDto groupUpdate(GroupDto groupDto) {
        return null;
    }
}
