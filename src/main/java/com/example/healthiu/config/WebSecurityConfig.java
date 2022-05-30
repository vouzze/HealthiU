package com.example.healthiu.config;

import com.example.healthiu.entity.Role;
import com.example.healthiu.security.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider authProvider;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("111111")).roles("ADMIN");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register").anonymous()
                .antMatchers("/home",
                        "/chat-messaging/**", "/test", "/test/result",
                        "/css/**", "/js/**", "/webjars/**"
                )
                .permitAll()
                .antMatchers("/chatroom/admin", "/chatroom/admin/add-chatroom/**", "/admin-register")
                .hasAuthority(Role.ADMIN.toString())
                .antMatchers("/chatroom/request-chatroom-doctor", "/chatroom/unrequest-chatroom-doctor")
                .hasAuthority(Role.DOCTOR.toString())
                .antMatchers("/chatroom/request-chatroom", "/chatroom/request-chatroom/requested")
                .hasAuthority(Role.USER.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.authenticationProvider(authProvider);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
