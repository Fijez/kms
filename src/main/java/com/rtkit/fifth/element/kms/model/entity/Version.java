package com.rtkit.fifth.element.kms.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "version")
public class Version implements Comparable {

    @EmbeddedId
    private VersionsId id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST})
    @ToString.Exclude
    @JoinColumn(insertable = false, updatable = false)
    private Article article;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User creator;

    @Column
    @NotBlank
    private String title;

    @Basic(fetch = FetchType.LAZY)
    @ToString.Exclude
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Version)) return false;
        Version version = (Version) o;
        return getCreator().equals(version.getCreator()) && getTitle().equals(version.getTitle()) && getContent().equals(version.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreator(), getTitle(), getContent());
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Version) {
            return ((Version) o).getId().getVersion().compareTo(this.getId().getVersion());
        } else {
            throw new RuntimeException("wrong object fore compare versions");
        }
    }
}
