package com.rtkit.fifth.element.kms.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(0)
    private long id;

    @NotBlank
    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "group")
    private List<ArticleGroup> articles;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> users;
}
