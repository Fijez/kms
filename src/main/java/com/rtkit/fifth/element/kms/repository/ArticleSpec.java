package com.rtkit.fifth.element.kms.repository;

import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.model.entity.ArticleGroup;
import com.rtkit.fifth.element.kms.model.entity.Namespace;
import com.rtkit.fifth.element.kms.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class ArticleSpec implements Specification<Article> {

    private final Optional<User> creator;
    private final Optional<String> title;
    private final Optional<String> topic;
    private final Optional<String> content;
    private final Optional<String[]> tags;

    private final Set<Namespace> namespaceSet;
    private final Set<List<ArticleGroup>> articleGroupListSet;
    private final User inquirer;

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        List<Predicate> tagPredicates = new ArrayList<>();

        List<Predicate> namespacePredicates = new ArrayList<>();
        List<Predicate> groupPredicates = new ArrayList<>();
        Predicate userPredicate;

        //search
        creator.ifPresent(cr -> predicates.add(builder.equal(root.get("creator"), cr)));
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

        //access check
        namespaceSet.forEach(namespace -> namespacePredicates.add(builder.equal(root.get("namespace"), namespace)));
        articleGroupListSet.forEach(articleGroupList -> articleGroupList.forEach(articleGroup -> groupPredicates.add(builder.isMember(articleGroup, root.get("groups")))));
        userPredicate = builder.isMember(inquirer, root.get("users"));

        predicates.add(builder.or(namespacePredicates.toArray(new Predicate[0])));
        predicates.add(builder.or(groupPredicates.toArray(new Predicate[0])));
        predicates.add(builder.or(userPredicate));

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
