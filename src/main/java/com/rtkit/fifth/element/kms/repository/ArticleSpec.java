package com.rtkit.fifth.element.kms.repository;

import com.rtkit.fifth.element.kms.model.entity.Article;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
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

        creator.filter(cr -> !cr.isBlank()).ifPresent(cr -> predicates.add(builder.equal(root.get("creator"), cr)));
        title.filter(ti -> !ti.isBlank()).ifPresent(ti -> predicates.add(builder.like(root.get("title"), ti)));
        topic.filter(to -> !to.isBlank()).ifPresent(to -> predicates.add(builder.like(root.get("topic"), to)));
        content.filter(co -> !co.isBlank()).ifPresent(
                co -> predicates.add(builder.equal(builder.function("fts", Boolean.class, root.get("content"), builder.literal(co)), true)));
        tags.filter(ta -> (ta.length > 0)).ifPresent(ta -> {
            for (String tag : ta) {
                tagPredicates.add(builder.in(root.get("tags")).value(tag));
            }
            predicates.add(builder.or(tagPredicates.toArray(new Predicate[0])));
        });
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
