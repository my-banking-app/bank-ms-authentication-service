package com.bank.mybankingappauthenticationservice.repository;

import com.bank.mybankingappauthenticationservice.enums.IdentificationType;
import com.bank.mybankingappauthenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByIdentificationTypeAndIdentificationNumber(IdentificationType identificationType, String identificationNumber);
}
