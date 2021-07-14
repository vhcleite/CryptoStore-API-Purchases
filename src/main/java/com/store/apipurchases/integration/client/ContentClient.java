package com.store.apipurchases.integration.client;

import com.store.apipurchases.integration.dto.ContentDto;
import com.store.apipurchases.integration.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "content", url = "http://localhost:8081/store/v1/content")
public interface ContentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{contentId}", produces = "application/json")
    public ContentDto getContentById(@PathVariable("contentId") Long contentId);
}
