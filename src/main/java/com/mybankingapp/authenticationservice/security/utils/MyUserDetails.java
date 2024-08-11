package com.mybankingapp.authenticationservice.security.utils;

import com.mybankingapp.authenticationservice.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementation of the UserDetails interface to provide user information
 * to Spring Security.
 */
public class MyUserDetails implements UserDetails {

    private final User user;

    /**
     * Constructor to initialize MyUserDetails with a User object.
     *
     * @param user the User object containing user details.
     */
    public MyUserDetails(User user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the user. In this implementation,
     * no authorities are granted.
     *
     * @return a collection of granted authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí puedes asignar roles o permisos específicos si es necesario
        return Collections.emptyList();
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the user's password.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used to authenticate the user. In this implementation,
     * the identification number is used as the username.
     *
     * @return the user's identification number.
     */
    @Override
    public String getUsername() {
        return user.getIdentificationNumber();  // Usamos el número de identificación como username
    }

    /**
     * Indicates whether the user's account has expired. In this implementation,
     * the account is always non-expired.
     *
     * @return true if the account is non-expired, false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. In this implementation,
     * the account is always non-locked.
     *
     * @return true if the account is non-locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. In this
     * implementation, the credentials are always non-expired.
     *
     * @return true if the credentials are non-expired, false otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. In this implementation,
     * the user is always enabled.
     *
     * @return true if the user is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the User object associated with this MyUserDetails instance.
     *
     * @return the User object.
     */
    public User getUser() {
        return this.user;
    }
}