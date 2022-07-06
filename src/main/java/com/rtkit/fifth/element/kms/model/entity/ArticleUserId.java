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
public class ArticleUserId implements Serializable {
    @Column(name = "article_id")
    private long articleId;
    @Column(name = "user_id")
    private long userId;
}
