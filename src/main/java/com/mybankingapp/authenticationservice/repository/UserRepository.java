package com.mybankingapp.authenticationservice.repository;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import com.mybankingapp.authenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository interface for User entities.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Finds a User by their identification type and identification number.
     *
     * @param identificationType the type of identification (e.g., passport, ID card)
     * @param identificationNumber the identification number
     * @return the User with the specified identification type and number, or null if no such user exists
     */
    User findByIdentificationTypeAndIdentificationNumber(IdentificationType identificationType, String identificationNumber);
    boolean existsByIdentificationTypeAndIdentificationNumber(IdentificationType identificationType, String identificationNumber);

}

