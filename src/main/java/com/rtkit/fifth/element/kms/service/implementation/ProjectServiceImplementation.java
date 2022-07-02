package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.ProjectDto;
import com.rtkit.fifth.element.kms.model.entity.Project;
import com.rtkit.fifth.element.kms.model.mapper.ProjectMapper;
import com.rtkit.fifth.element.kms.repository.ProjectRepo;
import com.rtkit.fifth.element.kms.service.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectServiceImplementation implements ProjectService {

    private final ProjectRepo projectRepo;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImplementation(ProjectRepo projectRepo, ProjectMapper projectMapper) {
        this.projectRepo = projectRepo;
        this.projectMapper = projectMapper;
    }

    @Override
    public Project getProject(String title) {
        return projectRepo.findByTitle(title);
    }


    @Override
    public ProjectDto getProjectDto(String title) {
        return projectMapper.modelToDto(getProject(title));
    }
}
