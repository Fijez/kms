package com.rtkit.fifth.element.kms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;

    @ManyToMany(mappedBy = "accessToArticles"
            , fetch = FetchType.LAZY)
    private Set<User> usersWithAccess;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @Column
    @NotBlank
    private String title;

    @Column
    private String author;

    @Column
    private String topic;

    @Column
    private LocalDate versionDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content;

    private Role roleAccess;

    @ManyToMany(mappedBy = "articles",
            fetch = FetchType.LAZY)
    private Set<Namespace> namespace;

    @ManyToMany(mappedBy = "articles",
            fetch = FetchType.LAZY)
    private Set<Tag> tags;

}
