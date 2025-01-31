package com.crudApi.service;

import com.crudApi.dto.UserRequest;
import com.crudApi.dto.UserResponse;
import jakarta.validation.Valid;

public interface UserService {
    UserResponse createUser(@Valid UserRequest request);
}