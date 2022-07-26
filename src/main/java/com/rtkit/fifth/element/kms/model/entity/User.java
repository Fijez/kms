package com.rtkit.fifth.element.kms.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @Min(0)
    private Long id;

    @OneToMany(mappedBy = "creator",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    private Set<Version> createdArticles;

    @OneToMany(mappedBy = "creator",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    private Set<Namespace> createdNamespaces;

    @ManyToMany(mappedBy = "users",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Group> groups;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH})
    private Set<ArticleUser> articles;

    @ManyToMany(mappedBy = "articles",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH})
    private Set<Namespace> Namespaces;

    @NotBlank
    @Email
    @Column
    private String email;

    @NotBlank
    @Size(min = 5, max = 20,
            message = "Не меньше 5 знаков и не более 20 знаков")
    @Column
    private String name;

    @NotBlank
    @Size(min = 5, message = "Не меньше 5 знаков")
    @Column
    private String password;

    @Transient
    private String passwordConfirm;

    private Role role;

}
