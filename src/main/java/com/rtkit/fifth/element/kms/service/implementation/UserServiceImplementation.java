package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.UserDto;
import com.rtkit.fifth.element.kms.model.dto.UserRegistrationInfo;
import com.rtkit.fifth.element.kms.model.entity.*;
import com.rtkit.fifth.element.kms.model.mapper.UserMapper;
import com.rtkit.fifth.element.kms.repository.UserRepo;
import com.rtkit.fifth.element.kms.service.MyUserDetails;
import com.rtkit.fifth.element.kms.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Stream;

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

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new MyUserDetails(user, this);
    }

    @Override
    public Collection<String> provideAuthorities(User user) {
        Set<String> authorities = new HashSet<>();
        authorities.add(user.getRole().getName());

        user.getGroups().forEach(group -> group.getArticles().forEach(articleGroup -> authorities.add(articleGroup.getGroupRole().getAuthority().toUpperCase() + articleGroup.getArticle().getTitle().toUpperCase())));
        user.getNamespaces().forEach(namespace -> namespace.getArticles().forEach(article -> authorities.add("REDACTOR" + article.getTitle().toUpperCase())));
        user.getArticles().forEach(articleUser -> authorities.add("REDACTOR" + articleUser.getArticle().getTitle().toUpperCase()));
        user.getCreatedArticles().forEach(article -> authorities.add("REDACTOR" + article.getTitle().toUpperCase()));

        return authorities;
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
