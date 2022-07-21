package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.entity.Group;

public interface GroupService {

 Group groupAdd (GroupDto groupDto);
 GroupDto groupUpdate (GroupDto groupDto);
}
