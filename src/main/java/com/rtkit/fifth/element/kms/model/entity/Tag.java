package com.rtkit.fifth.element.kms.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class Tag implements Serializable {

    @Id
    private String title;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.REFRESH})
    private List<Article> articles;
}
