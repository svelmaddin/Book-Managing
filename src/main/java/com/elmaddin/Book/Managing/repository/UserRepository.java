package com.elmaddin.Book.Managing.repository;

import com.elmaddin.Book.Managing.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String userName);
    UserEntity findFirstByUsername(String username);
    UserEntity findByEmail(String email);
}
