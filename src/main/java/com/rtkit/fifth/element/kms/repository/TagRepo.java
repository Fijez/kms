package com.rtkit.fifth.element.kms.repository;

import com.rtkit.fifth.element.kms.model.entity.Tag;
import com.rtkit.fifth.element.kms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
    Tag findByTitle(String title);
}
