package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.entity.User;
import com.rtkit.fifth.element.kms.model.user.info.UserRegistrationInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findUserById(Long userId);

    List<User> allUsers();

    boolean saveUser(UserRegistrationInfo user);

    boolean deleteUser(Long userId);
}