package com.example.demo.spring.security.jwt.config;

import com.example.demo.spring.security.jwt.filter.CustomAuthenticationFilter;
import com.example.demo.spring.security.jwt.filter.CustomAuthorizationFilter;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("myUserDetailsService")
    private final UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/**","/api/users/**").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.POST, "/api/user/save/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilter(filter)
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
