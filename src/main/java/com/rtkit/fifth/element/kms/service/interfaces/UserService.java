package com.rtkit.fifth.element.kms.service.interfaces;

import com.rtkit.fifth.element.kms.model.dto.UserDto;
import com.rtkit.fifth.element.kms.model.dto.UserRegistrationInfo;
import com.rtkit.fifth.element.kms.model.entity.User;
import com.rtkit.fifth.element.kms.model.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findUserById(Long userId);

    User findUserByMail(String login);

    List<User> allUsers();

    boolean saveUser(UserRegistrationInfo user);

    boolean deleteUser(Long userId);

    String encodePassword(String password);

    public UserDto map(User user);

    public List<UserDto> map(List<User> users);
}