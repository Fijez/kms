package com.rtkit.fifth.element.kms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "articles_users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
