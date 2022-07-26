package com.rtkit.fifth.element.kms.repository;

import com.rtkit.fifth.element.kms.model.entity.Version;
import com.rtkit.fifth.element.kms.model.entity.VersionsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionRepo extends JpaRepository<Version, VersionsId> {
}
