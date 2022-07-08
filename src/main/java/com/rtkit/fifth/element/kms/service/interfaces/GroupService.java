package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.entity.Group;

public interface GroupService {

    Group getGroup(String title);

    GroupDto getGroupDto(String title);
}
