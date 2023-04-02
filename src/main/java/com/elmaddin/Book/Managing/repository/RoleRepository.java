package com.elmaddin.Book.Managing.repository;


import com.elmaddin.Book.Managing.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
