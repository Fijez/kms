package com.rtkit.fifth.element.kms.repository;

import com.rtkit.fifth.element.kms.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProjectRepo  extends JpaRepository<Project, Long> {

    Project findByTitle(String name);
}
