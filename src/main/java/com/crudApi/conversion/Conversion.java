package com.crudApi.conversion;


import com.crudApi.dto.UserRequest;
import com.crudApi.dto.UserResponse;
import com.crudApi.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Conversion {

    public User dtoToEntity(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        return user;
    }

    public UserResponse entityToDto(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        return response;
    }
}