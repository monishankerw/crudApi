package com.crudApi.service.impl;

import com.crudApi.dto.UserRequest;
import com.crudApi.dto.UserResponse;
import com.crudApi.entity.User;
import com.crudApi.exception.UserNotFoundException;
import com.crudApi.repository.UserRepository;
import com.crudApi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {
        log.info("Creating a new user: {}", request);
        User user = dtoToEntity(request);
        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        return entityToDto(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) {
        log.info("Fetching user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });
        log.info("User fetched successfully: {}", user);
        return entityToDto(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Fetching all users...");
        List<UserResponse> userResponses = userRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
        log.info("Fetched {} users", userResponses.size());
        return userResponses;
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        log.info("Updating user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        User updatedUser = userRepository.save(user);
        log.info("User updated successfully: {}", updatedUser);
        return entityToDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);
        if (!userRepository.existsById(id)) {
            log.error("User not found with ID: {}", id);
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        log.info("User deleted successfully with ID: {}", id);
    }

    // Mapper methods
    private User dtoToEntity(UserRequest request) {
        log.debug("Mapping UserRequest to User entity: {}", request);
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        return user;
    }

    private UserResponse entityToDto(User user) {
        log.debug("Mapping User entity to UserResponse: {}", user);
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        return response;
    }
}