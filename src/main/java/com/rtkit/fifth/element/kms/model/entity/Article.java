package com.rtkit.fifth.element.kms.model.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Positive
    private Long id;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;

    @OneToMany(mappedBy = "article",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH})
    @ToString.Exclude
    private Set<ArticleUser> users;

    @OneToMany(mappedBy = "article",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH})
    @ToString.Exclude
    private List<ArticleGroup> groups;

    @Column
    @NotBlank
    private String title;

    @Column
    private String topic;

    @Column
    @DateTimeFormat
    private LocalDateTime versionDate;

    @Basic(fetch = FetchType.LAZY)
    @ToString.Exclude
    private String content;

    private Role roleAccess;


    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @ToString.Exclude
    private Namespace namespace;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "tag_articles",
            joinColumns = @JoinColumn(name = "articles_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    private Set<Tag> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Article article = (Article) o;
        return id != null && Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }
}
