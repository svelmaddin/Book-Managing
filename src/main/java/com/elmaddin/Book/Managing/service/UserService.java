package com.elmaddin.Book.Managing.service;


import com.elmaddin.Book.Managing.dto.RegistrationDto;
import com.elmaddin.Book.Managing.model.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);

}
