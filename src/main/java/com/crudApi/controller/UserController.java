package com.crudApi.controller;

import com.crudApi.constant.Constants;
import com.crudApi.dto.ApiResponse;
import com.crudApi.dto.UserRequest;
import com.crudApi.dto.UserResponse;
import com.crudApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return new ApiResponse<>(true, Constants.USER_CREATED, userResponse);
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return new ApiResponse<>(true, Constants.USER_CREATED, userResponse);
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = userService.getAllUsers();
        return new ApiResponse<>(true, Constants.USER_CREATED, userResponses);
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        UserResponse userResponse = userService.updateUser(id, request);
        return new ApiResponse<>(true, Constants.USER_UPDATED, userResponse);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ApiResponse<>(true, Constants.USER_DELETED, null);
    }
}