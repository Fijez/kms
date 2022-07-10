package com.rtkit.fifth.element.kms.repository;

import com.rtkit.fifth.element.kms.model.entity.Article;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ArticleSpec implements Specification<Article> {

    private final Optional<String> creator;
    private final Optional<String> title;
    private final Optional<String> topic;
    private final Optional<String> content;
    private final Optional<String[]> tags;

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        List<Predicate> tagPredicates = new ArrayList<>();

        if (creator.isPresent() && !creator.get().isBlank()) {
            predicates.add(builder.equal(root.get("creator"), creator.get()));
        }
        if (title.isPresent() && !title.get().isBlank()) {
            predicates.add(builder.like(root.get("title"), title.get()));
        }
        if (topic.isPresent() && !topic.get().isBlank()) {
            predicates.add(builder.like(root.get("topic"), topic.get()));
        }
        if (content.isPresent() && !content.get().isBlank()) {
            predicates.add(builder.like(root.get("content"), ".*" + content.get() + ".*"));
        }
        if (tags.isPresent()) {
            for (String tag : tags.get()
            ) {
                tagPredicates.add(builder.in(root.get("tags")).value(tag));
            }
            predicates.add(builder.or(tagPredicates.toArray(new Predicate[0])));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
