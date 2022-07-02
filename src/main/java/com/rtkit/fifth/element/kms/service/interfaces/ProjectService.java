package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.dto.ProjectDto;
import com.rtkit.fifth.element.kms.model.entity.Project;

public interface ProjectService {

    Project getProject(String title);

    ProjectDto getProjectDto(String title);
}
