package com.example.emmanager.repo;

import com.example.emmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);

    @Query(
            value = "SELECT * FROM users WHERE username = :username LIMIT 1",
            nativeQuery = true)
    Optional<User> findUserByUsername(@Param("username") String username);
}
