package com.rtkit.fifth.element.kms.config;

import com.rtkit.fifth.element.kms.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] ALL = {"/", "/resources/**"};
    private static final String[] UNAUTHORIZED = {"/registration"};
    private static final String[] UI = {"/swagger-ui/**"};
    private static final String[] ADMIN = {"/admin/**"};
    private static final String[] AUTHORIZED = {"/search"};

    private static final String LOGIN_PAGE = "/login";
    private static final String INDEX_PAGE = "/";
    private static final String ADMIN_ROLE = "ADMIN";

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(UI).permitAll()
                .antMatchers(UNAUTHORIZED).not().fullyAuthenticated()
                .antMatchers(ADMIN).hasRole(ADMIN_ROLE)
                .antMatchers(ALL).permitAll()
                .antMatchers(AUTHORIZED).authenticated()
                .anyRequest().authenticated()
                .and().formLogin().loginPage(LOGIN_PAGE)
                .defaultSuccessUrl(INDEX_PAGE).permitAll()
                .and().logout().permitAll().logoutSuccessUrl(INDEX_PAGE);
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}