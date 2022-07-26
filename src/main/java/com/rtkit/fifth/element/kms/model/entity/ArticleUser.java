package com.rtkit.fifth.element.kms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "articles_users")
public class ArticleUser {


    @EmbeddedId
    private ArticleUserId id;

    private Role userRole;

    public ArticleUser(User user, Article article, Role userRole) {
        this.id = new ArticleUserId(article.getId(), user.getId());
        this.userRole = userRole;
        this.article = article;
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "articleId")
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "userId")
    @JoinColumn(name = "user_id")
    private User user;

}
