package com.viseo.c360.formation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
*/
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.httpBasic().and()
                .authorizeRequests()
                .antMatchers("/apis**").permitAll();
                //.anyRequest()
                //.authenticated();
    }
}
