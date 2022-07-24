package com.rtkit.fifth.element.kms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Role implements GrantedAuthority {

    USER("user", 1),
    MODERATOR("moderator", 2),
    ADMIN("admin", 3),
    ZERO_ADMIN("zero_admin", 4);

    private String name;
    private int priority;

    @Override
    public String getAuthority() {
        return getName();
    }
}
