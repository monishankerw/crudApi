package com.crudApi.controller;

import com.crudApi.constant.Constants;
import com.crudApi.dto.ApiResponse;
import com.crudApi.dto.UserRequest;
import com.crudApi.dto.UserResponse;
import com.crudApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return new ApiResponse<>(true, Constants.USER_CREATED, userResponse);
    }
}