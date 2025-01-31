package com.crudApi.service.impl;

import com.crudApi.conversion.Conversion;
import com.crudApi.dto.UserRequest;
import com.crudApi.dto.UserResponse;
import com.crudApi.entity.User;
import com.crudApi.repository.UserRepository;
import com.crudApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Conversion conversion;


    @Override
    public UserResponse createUser(UserRequest request) {
            User user = conversion.dtoToEntity(request);
            User savedUser = userRepository.save(user);
            return conversion.entityToDto(savedUser);
        }
    }
