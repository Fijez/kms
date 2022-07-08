package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.entity.Group;
import com.rtkit.fifth.element.kms.model.mapper.GroupMapper;
import com.rtkit.fifth.element.kms.repository.GroupRepo;
import com.rtkit.fifth.element.kms.service.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupServiceImplementation implements GroupService {

    private final GroupRepo groupRepo;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupServiceImplementation(GroupRepo groupRepo, GroupMapper groupMapper) {
        this.groupRepo = groupRepo;
        this.groupMapper = groupMapper;
    }

    @Override
    public Group getGroup(String title) {
        return groupRepo.findByTitle(title);
    }


    @Override
    public GroupDto getGroupDto(String title) {
        return groupMapper.modelToDto(getGroup(title));
    }
}
