package org.alkemy.challenge1.services;

import org.alkemy.challenge1.domain.User;
import org.alkemy.challenge1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository repository;

    public User registerUser (String email, String password) {
        User usr = new User();

        usr.setEmail(email);
        usr.setPassword(passwordEncoder.encode(password));

        return repository.save(usr);
    }

    public void saveUser (User usr) {
        repository.save(usr);
    }

    public Boolean emailExists (String email) {
        return repository.existsByEmail(email);
    }
 }
