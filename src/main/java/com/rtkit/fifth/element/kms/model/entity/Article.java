package com.rtkit.fifth.element.kms.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
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

    @OneToMany(mappedBy = "article",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH})
    private Set<ArticleUser> users;

    @OneToMany(mappedBy = "article",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH})
    private List<ArticleGroup> groups;

    @Column
    @NotBlank
    private String title;

    @Column
    private String author;

    @Column
    private String topic;

    @Column
    private Date versionDate;

    @Lob //Не работает как нужно (сохраняет в непонятном виде)
    @Basic(fetch = FetchType.LAZY)
    private String content;

    private Role roleAccess;


    @ManyToMany(mappedBy = "articles",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.REFRESH})
    private Set<Namespace> namespace;

    @ManyToMany(mappedBy = "articles",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST})
    private Set<Tag> tags;

}
