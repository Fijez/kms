package com.rtkit.fifth.element.kms.model.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_generator")
    private Long id;

    @Column(unique = true)
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "group",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH})
    @ToString.Exclude
    private Set<ArticleGroup> articles;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "groups_users",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<User> users;
}
