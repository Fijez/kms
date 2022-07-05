package com.rtkit.fifth.element.kms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Set;

@Data
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
    private LocalDate versionDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content;

    private Role roleAccess;

    @ManyToMany(mappedBy = "articles")
    private Set<Namespace> namespace;

    @ManyToMany(mappedBy = "articles")
    private Set<Tag> tags;

}
