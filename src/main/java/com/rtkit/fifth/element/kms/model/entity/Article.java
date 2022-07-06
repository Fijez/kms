package com.rtkit.fifth.element.kms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive
    @Column(name = "id")
    private long id;

    @NotNull
    @ManyToOne
    private User creator;

    @ManyToMany(mappedBy = "accessToArticles")
    private Set<User> usersWithAccess;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @Column
    @NotBlank
    private String title;

    @Column
    private String author;

    @Column
    private String topic;

    @Column
    private Date versionDate;

    @Lob //Ќе работает как нужно (сохран€ет в непон€тном виде)
    @Basic(fetch = FetchType.LAZY)
    private String content;

    private Role roleAccess;

    @ManyToMany(mappedBy = "articles")
    private Set<Namespace> namespace;

    @ManyToMany(mappedBy = "articles")
    private Set<Tag> tags;

}
