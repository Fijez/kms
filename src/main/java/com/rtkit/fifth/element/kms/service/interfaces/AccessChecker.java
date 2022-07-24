package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.entity.Article;
import org.springframework.security.core.Authentication;

public interface AccessChecker {

    void checkArticleAccess(Article article, Authentication authentication);
}
