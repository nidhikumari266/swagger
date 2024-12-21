package com.swagger.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.swagger.entity.User;
import com.swagger.globalexception.OptimisticLockingException;
import com.swagger.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    
    @Transactional
    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create user", e);
        }
    }


    
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found Successfully"));
        
    }

    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    
    @Transactional
    public void updateUser(User user) {
        try {
            userRepository.save(user);
        } catch (ObjectOptimisticLockingFailureException e) {
            // Handle concurrency issue and provide more specific feedback
            throw new OptimisticLockingException("This record was updated by another user. Please refresh and try again.");
        } catch (Exception e) {
            // General exception handling
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
