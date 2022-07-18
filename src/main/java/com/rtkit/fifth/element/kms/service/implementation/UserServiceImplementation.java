package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.UserDto;
import com.rtkit.fifth.element.kms.model.dto.UserRegistrationInfo;
import com.rtkit.fifth.element.kms.model.entity.*;
import com.rtkit.fifth.element.kms.model.mapper.UserMapper;
import com.rtkit.fifth.element.kms.repository.UserRepo;
import com.rtkit.fifth.element.kms.service.MyUserDetails;
import com.rtkit.fifth.element.kms.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class UserServiceImplementation implements UserService, UserDetailsService {

    @PersistenceContext
    private EntityManager em;
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepo userRepo, UserMapper userMapper,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public UserDto map(User user) {
        return userMapper.modelToDto(user);
    }

    @Override
    public List<UserDto> map(List<User> users) {
        return userMapper.modelToDto(users);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        Set<String> authorities = new HashSet<>();

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        for (Group group : user.getGroups()
        ) {
            for (ArticleGroup articleGroup : group.getArticles()) {
                authorities.add("REDACTOR" + articleGroup.getArticle().getId());
            }
        }
        for (Namespace namespace : user.getNamespaces()
        ) {
            for (Article article : namespace.getArticles()) {
                authorities.add("REDACTOR" + article.getId());
            }
        }

        return new MyUserDetails(user, authorities);
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepo.findById(userId);
        return userFromDb.orElse(new User());
    }

    @Override
    public User findUserByMail(String login) {
        return userRepo.findByEmail(login);
    }

    @Override
    public List<User> allUsers() {
        return userRepo.findAll();
    }

    @Override
    @Transactional
    public boolean saveUser(UserRegistrationInfo registrationInfo) {
        User userFromDB = userRepo.findByEmail(registrationInfo.getEmail());

        if (userFromDB != null) {
            return false;
        }

        userRepo.save(
                User.builder()
                        .email(registrationInfo.getEmail())
                        .name(registrationInfo.getName())
                        .password(passwordEncoder.encode(registrationInfo.getPassword()))
                        .role(Role.USER)
                        .build());

        return true;
    }

    @Override
    public boolean deleteUser(Long userId) {
        if (userRepo.findById(userId).isPresent()) {
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }
}
