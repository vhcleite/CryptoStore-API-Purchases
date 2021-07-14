package com.store.apipurchases.integration.service;

import com.store.apipurchases.integration.client.ContentClient;
import com.store.apipurchases.integration.dto.ContentDto;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    private ContentClient client;

    public ContentService(ContentClient client) {
        this.client = client;
    }

    public ContentDto findById(Long contentId) {
        return this.client.getContentById(contentId);
    }
}
