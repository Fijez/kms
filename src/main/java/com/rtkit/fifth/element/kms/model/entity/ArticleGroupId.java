package com.rtkit.fifth.element.kms.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
class ArticleGroupId implements Serializable {
    @Column(name = "article_id")
    private long articleId;
    @Column(name = "group_id")
    private long groupId;
}