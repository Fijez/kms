package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.entity.Article;
import com.rtkit.fifth.element.kms.service.interfaces.AccessChecker;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional
public class AccessCheckerImpl implements AccessChecker {

    @Override
    public void checkArticleAccess(Article article, Authentication authentication) {
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new RuntimeException("Unauthorized");
        }

        AtomicBoolean accessibleByGroup = new AtomicBoolean(false);
        AtomicBoolean accessibleByUsers = new AtomicBoolean(false);
        boolean accessibleByNamespace = false;

        var grantedAuthorities = authentication.getAuthorities();
        Set<String> authorities = new HashSet<>();
        grantedAuthorities.forEach(a -> authorities.add(a.getAuthority()));


        article.getGroups().forEach(articleGroup -> {
            if (authorities.contains(articleGroup.getGroup().getTitle().toUpperCase())) {
                accessibleByGroup.set(true);
            }
        });

        article.getUsers().forEach(articleUser -> {
            if (authorities.contains(articleUser.getUserRole().getAuthority().toUpperCase() + "_" + articleUser.getArticle().getVersions().last().getTitle().toUpperCase())) {
                accessibleByUsers.set(true);
            }
        });

        if (article.getNamespace() != null && authorities.contains(article.getNamespace().getTitle().toUpperCase())) {
            accessibleByNamespace = true;
        }

        if (!(accessibleByGroup.get() || accessibleByUsers.get() || accessibleByNamespace)) {
            throw new RuntimeException("access denied");
        }
    }
}
