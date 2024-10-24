package com.acs560.restaurantsales.restaurant_sales.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.acs560.restaurantsales.restaurant_sales.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Allow access to images (e.g., for the logo or other public resources)
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers(
                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/images/*.png")).permitAll());

        // Secure the rest of the application
        super.configure(http);

        // Define the login view for Vaadin
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService users() {
        // Create a simple in-memory user store with two users: "user" and "admin"
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}userpass") // "{noop}" means no encoding (plain text password)
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}adminpass")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
