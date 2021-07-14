package com.store.apipurchases.integration.service;

import com.store.apipurchases.integration.client.UserClient;
import com.store.apipurchases.integration.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserClient userClient;

    @Autowired
    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public UserDto getUserById(String userId) {
        return userClient.getUserById(userId);
    }
}
