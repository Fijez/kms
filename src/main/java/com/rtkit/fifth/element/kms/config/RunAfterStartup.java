package com.rtkit.fifth.element.kms.config;

import com.rtkit.fifth.element.kms.model.entity.Namespace;
import com.rtkit.fifth.element.kms.model.entity.Role;
import com.rtkit.fifth.element.kms.model.entity.User;
import com.rtkit.fifth.element.kms.service.interfaces.NamespaceService;
import com.rtkit.fifth.element.kms.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class RunAfterStartup {
    private final String ADMIN_PASSWORD = "admin password";
    private final String ADMIN_MAIL = "admin@mail.ru";
    private final String ADMIN_NAME = "zero_admin";
    UserService userService;

    NamespaceService namespaceService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {

        User user = User.builder()
                .email(ADMIN_MAIL)
                .name(ADMIN_NAME)
                .password(ADMIN_PASSWORD)
                .role(Role.ZERO_ADMIN)
                .build();

        Namespace namespace = Namespace.builder()
                .title("open namespace")
                .build();
        userService.saveUser(user);
        namespaceService.addNewNamespace(namespace);
    }
}