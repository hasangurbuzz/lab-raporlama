package com.hasan.laboratuvarraporlama.security;


import com.hasan.laboratuvarraporlama.security.auth.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

import static com.hasan.laboratuvarraporlama.security.AppUserPermission.*;
import static com.hasan.laboratuvarraporlama.security.AppUserRole.ADMIN;
import static com.hasan.laboratuvarraporlama.security.AppUserRole.USER;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, AppUserService appUserService) {
        this.passwordEncoder = passwordEncoder;
        this.appUserService = appUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/report/").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers("/report/add/**").hasAuthority(REPORT_WRITE.getPermission())
                .antMatchers("/report/update/**").hasAuthority(REPORT_WRITE.getPermission())
                .antMatchers("/report/search/**").hasAuthority(REPORT_READ.getPermission())
                .antMatchers("/report/delete/**").hasAuthority(REPORT_DELETE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/report", true)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7))
                .key("secretkey")
                .rememberMeParameter("remember-me")
                .userDetailsService(appUserService)
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login");


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }


}
