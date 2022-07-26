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

@Data
public class ArticleSpecification implements Specification<Article> {
    private List<ArticleSearchCriteria> list = new ArrayList<>();

    public void add(ArticleSearchCriteria criteria) {
        list.add(criteria);
    }

    public void add(List<ArticleSearchCriteria> criteria) {
        list.addAll(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (ArticleSearchCriteria criteria : list) {
            switch (criteria.getOperation()) {
                case GREATER_THAN:
                    predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case LESS_THAN:
                    predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case GREATER_THAN_EQUAL:
                    predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case LESS_THAN_EQUAL:
                    predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case NOT_EQUAL:
                    predicates.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case EQUAL:
                    predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case LIKE:
                    predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case LIKE_END:
                    predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case LIKE_START:
                    predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase()));
                    break;
                case IN:
                    predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
                    break;
                case NOT_IN:
                    predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
                    break;
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
