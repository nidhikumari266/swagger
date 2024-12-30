package com.swagger.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.swagger.entity.User;
import com.swagger.enumration.Role;
import com.swagger.globalexception.OptimisticLockingException;
import com.swagger.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    
    @Transactional
    public User createUser(User user) {
    	String uuid = UUID.randomUUID().toString();
    	long id = Math.abs(uuid.hashCode());
    	user.setId(id);
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
    
    public List<User> searchUsers(String name, String email, Role role) {
        return userRepository.searchUsers(
            name == null || name.isBlank() ? null : name,
            email == null || email.isBlank() ? null : email,
            role
        );
    }
}
