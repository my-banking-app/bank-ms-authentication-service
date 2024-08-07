package com.mybankingapp.authenticationservice.repository;

import com.mybankingapp.authenticationservice.enums.IdentificationType;
import com.mybankingapp.authenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByIdentificationTypeAndIdentificationNumber(IdentificationType identificationType, String identificationNumber);
}
