package com.rtkit.fifth.element.kms.repository;

import com.rtkit.fifth.element.kms.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface TagRepo extends JpaRepository<Tag, Long> {

    Tag findByTitle(String title);
}
