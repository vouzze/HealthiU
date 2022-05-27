package com.example.healthiu.repository;

import com.example.healthiu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    User findUserById(Long id);

    User findUserByLogin(String login);

    User findUserByEmail(String email);
}
