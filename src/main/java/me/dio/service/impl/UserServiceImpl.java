package me.dio.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.model.User;
import me.dio.repository.UserRepository;
import me.dio.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new IllegalArgumentException("Usuário já cadastrado");
        }

        return userRepository.save(user);
    }
    
}
