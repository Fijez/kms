package com.rtkit.fifth.element.kms.model.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(0)
    private long id;

    @OneToMany(mappedBy = "creator",
            fetch = FetchType.LAZY)
    private Set<Article> createdArticles;

    @OneToMany(mappedBy = "creator",
            fetch = FetchType.LAZY)
    private Set<Namespace> createdNamespaces;

    @ManyToMany(mappedBy = "users",
            fetch = FetchType.LAZY)
    private Set<Group> groups;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Article> accessToArticles;

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

    @ManyToMany(mappedBy = "articles",
            fetch = FetchType.LAZY)
    private Set<Namespace> Namespaces;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getRole());
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
