package org.example.beutysalonsystem.service;

import org.example.beutysalonsystem.model.AdminUser;
import org.example.beutysalonsystem.model.RegularUser;
import org.example.beutysalonsystem.model.User;
import org.example.beutysalonsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // CREATE - Register new user
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // READ - Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ - Get user by ID
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    // READ - Search by username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // READ - Search by keyword (username or email)
    public List<User> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword);
    }

    // UPDATE
    public User updateUser(int id, User updatedUser) {
        User existing = userRepository.findById(id).orElseThrow();
        existing.setUsername(updatedUser.getUsername());
        existing.setEmail(updatedUser.getEmail());
        existing.setPhone(updatedUser.getPhone());
        existing.setPassword(updatedUser.getPassword());
        return userRepository.save(existing);
    }

    // DELETE
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // POLYMORPHISM - different behavior based on user type
    public String getUserAccessInfo(User user) {
        if (user instanceof AdminUser admin) {
            return admin.getAdminPrivileges();
        } else if (user instanceof RegularUser regular) {
            return regular.getAccessLevel();
        } else {
            return "Standard access";
        }
    }
}