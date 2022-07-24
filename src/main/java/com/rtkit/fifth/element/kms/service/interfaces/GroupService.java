package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.dto.GroupDto;
import com.rtkit.fifth.element.kms.model.dto.GroupUpdateDto;

public interface GroupService {

    GroupDto groupAdd(GroupDto groupDto);

    GroupUpdateDto groupUpdate(GroupUpdateDto groupDto);
}
