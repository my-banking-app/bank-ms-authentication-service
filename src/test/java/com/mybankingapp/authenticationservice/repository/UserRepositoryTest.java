package com.mybankingapp.authenticationservice.repository;

import com.mybankingapp.authenticationservice.enums.Gender;
import com.mybankingapp.authenticationservice.enums.IdentificationType;
import com.mybankingapp.authenticationservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User createValidUser() {
        User user = new User();
        user.setIdentificationType(IdentificationType.PA);
        user.setIdentificationNumber("AB123456");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setCityOfResidence("New York");
        user.setNationality("American");
        user.setGender(Gender.MALE);
        user.setPassword("StrongP@ssword123");
        user.setCivilStatus("Single");
        user.setAge(30);
        user.setPhoneNumber("1234567890");
        user.setEmail("john.doe@example.com");
        user.setDataProcessingAgreement(true);
        return user;
    }

    @Test
    public void whenFindByIdentificationTypeAndNumber_thenReturnUser() {
        // given
        User user = createValidUser();
        entityManager.persist(user);
        entityManager.flush();

        // when
        User found = userRepository.findByIdentificationTypeAndIdentificationNumber(
                IdentificationType.PA, "AB123456");

        // then
        assertThat(found).isNotNull();
        assertThat(found.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(found.getLastName()).isEqualTo(user.getLastName());
    }

    @Test
    public void whenFindByNonExistentIdentification_thenReturnNull() {
        // when
        User found = userRepository.findByIdentificationTypeAndIdentificationNumber(
                IdentificationType.CC, "NonExistent");

        // then
        assertThat(found).isNull();
    }

    @Test
    public void whenSaveUser_thenFindById() {
        // given
        User user = createValidUser();

        // when
        User savedUser = userRepository.save(user);
        Optional<User> found = userRepository.findById(savedUser.getId());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getLastName()).isEqualTo(user.getLastName());
    }

    @Test
    public void whenDeleteUser_thenUserShouldNotExist() {
        // given
        User user = createValidUser();
        User savedUser = userRepository.save(user);

        // when
        userRepository.deleteById(savedUser.getId());
        Optional<User> found = userRepository.findById(savedUser.getId());

        // then
        assertThat(found).isEmpty();
    }
}