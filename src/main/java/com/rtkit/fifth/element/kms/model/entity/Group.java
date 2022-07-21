package com.rtkit.fifth.element.kms.model.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "group",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH})
    @ToString.Exclude
    private List<ArticleGroup> articles;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> users;
}
