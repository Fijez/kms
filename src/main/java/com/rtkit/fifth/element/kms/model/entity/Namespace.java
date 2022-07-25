package com.rtkit.fifth.element.kms.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "namespace")
public class Namespace {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "namespace_id_generator")
    @Min(0)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH})
    @JoinTable(name = "namespace_users",
            joinColumns = @JoinColumn(name = "namespace_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;

    @OneToMany(mappedBy = "namespace",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    private Set<Article> articles;
}
