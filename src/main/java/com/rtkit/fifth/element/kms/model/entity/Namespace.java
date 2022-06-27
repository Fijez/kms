package com.rtkit.fifth.element.kms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "namespace", uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
public class Namespace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(0)
    private long id;

    @Column
    private String title;

    @ManyToMany
    private Set<User> users;

    @ManyToOne
    private User creator;

    @ManyToMany
    private Set<Article> articles;
}
