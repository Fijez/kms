package com.rtkit.fifth.element.kms.service.implementation;

import com.rtkit.fifth.element.kms.model.dto.UserDto;
import com.rtkit.fifth.element.kms.model.dto.UserRegistrationInfo;
import com.rtkit.fifth.element.kms.model.entity.Role;
import com.rtkit.fifth.element.kms.model.entity.User;
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
@Transactional

public class UserServiceImplementation implements UserService, UserDetailsService {

    @PersistenceContext
    private EntityManager em;
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

//    @PostConstruct
//    public void init(){
//        User admin = new User();
//        admin.setEmail("admin@mail.ru");
//        admin.setRole(Role.ADMIN);
//        String adminPass = passwordEncoder.encode("admin");
//        admin.setPassword(adminPass);
//        if (!admin.equals(userRepo.findByEmail("admin@mail.ru"))) {
//            userRepo.save(admin);
//        }
//    }

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

//      user.getGroups().forEach(group -> group.getArticles().forEach(articleGroup -> authorities.add(articleGroup.getGroupRole().getAuthority().toUpperCase() + "_" + articleGroup.getArticle().getTitle().toUpperCase())));
        user.getGroups().forEach(group -> authorities.add(group.getTitle().toUpperCase()));
//      user.getNamespaces().forEach(namespace -> namespace.getArticles().forEach(article -> authorities.add(namespace.getTitle().toUpperCase() + "_" + article.getTitle().toUpperCase())));
        user.getNamespaces().forEach(namespace -> authorities.add(namespace.getTitle().toUpperCase()));
        user.getArticles().forEach(articleUser -> authorities.add(articleUser.getUserRole().getAuthority().toUpperCase() + "_" + articleUser.getArticle().getTitle().toUpperCase()));

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
