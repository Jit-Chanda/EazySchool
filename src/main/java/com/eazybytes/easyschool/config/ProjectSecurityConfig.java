package com.eazybytes.easyschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {

        //to allow all request inside the web application
//        http.authorizeRequests().anyRequest().permitAll()
//                .and().formLogin()
//                .and().httpBasic();

        //to deny all request inside the web application
//        http.authorizeRequests().anyRequest().denyAll()
//                .and().formLogin()
//                .and().httpBasic();

        http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/h2-console/**").and()
                .authorizeRequests()
                .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/home").permitAll()
                .mvcMatchers("/holidays/**").permitAll()
                .mvcMatchers("/contact").permitAll()
                .mvcMatchers("/saveMsg").permitAll()
                .mvcMatchers("/courses").permitAll()
                .mvcMatchers("/about").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and().httpBasic();

        http.headers().frameOptions().disable();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("12345").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("USER", "ADMIN")
                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
