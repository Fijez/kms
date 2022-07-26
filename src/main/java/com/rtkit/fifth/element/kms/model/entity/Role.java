package com.rtkit.fifth.element.kms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Role implements GrantedAuthority {

    USER("USER", 1),
    MODERATOR("MODERATOR", 2),
    ADMIN("ADMIN", 3);

    private String name;
    private int priority;

    @Override
    public String getAuthority() {
        return getName();
    }
}
