package com.rtkit.fifth.element.kms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "articles_users")
public class ArticleUser {


    @EmbeddedId
    ArticleUserId id;

    private Role userRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "articleId")
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "userId")
    @JoinColumn(name = "user_id")
    private User user;
}
