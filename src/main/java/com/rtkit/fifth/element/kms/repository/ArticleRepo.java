package com.rtkit.fifth.element.kms.repository;

import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.Namespace;
import com.rtkit.fifth.element.kms.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public interface ArticleRepo extends JpaRepository<Article, Long>, JpaSpecificationExecutor {
    //накидал быстрый пример
    List<Article> findByTitleAndAuthorAndTopicAndVersionDateAndProjectAndNamespace(
            String title, String author, String topic, LocalDate versionDate, Project project,
            Set<Namespace> namespace
    );
}
