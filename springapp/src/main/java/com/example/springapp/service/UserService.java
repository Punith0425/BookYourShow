package com.example.springapp.service;

import com.example.springapp.model.User;
import com.example.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 🔹 Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 🔹 Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // 🔹 Get user by email (JPQL query)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 🔹 Create new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // 🔹 Update existing user
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // 🔹 Delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // ✅ Paginated & Sorted Users
    public Page<User> getAllUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }
}
