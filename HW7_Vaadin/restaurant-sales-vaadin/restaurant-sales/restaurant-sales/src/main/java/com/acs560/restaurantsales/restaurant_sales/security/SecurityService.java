package com.acs560.restaurantsales.restaurant_sales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.security.AuthenticationContext;

/**
 * Security service class for handling authentication and logout functionality.
 */
@Component
public class SecurityService {

    private final AuthenticationContext authenticationContext;

    @Autowired
    public SecurityService(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return the authenticated user's details, or null if no user is authenticated.
     */
    public UserDetails getAuthenticatedUser() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class).orElse(null);
    }

    /**
     * Logs out the currently authenticated user.
     */
    public void logout() {
        authenticationContext.logout();
    }
}
