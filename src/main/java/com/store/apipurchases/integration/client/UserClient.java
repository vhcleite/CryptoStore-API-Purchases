package com.store.apipurchases.integration.client;

import com.store.apipurchases.integration.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user", url = "http://localhost:8082/store/v1/users")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}", produces = "application/json")
    public UserDto getUserById(@PathVariable("userId") String userId);
}
