package com.rtkit.fifth.element.kms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "articles_groups")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleGroup {


    @EmbeddedId
    ArticleGroupId id;

    private Role groupRole;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "articleId")
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "groupId")
    @JoinColumn(name = "group_id")
    private Group group;
}




