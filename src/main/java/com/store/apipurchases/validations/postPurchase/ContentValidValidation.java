package com.store.apipurchases.validations.postPurchase;

import com.store.apipurchases.exceptions.CryptoStoreException;
import com.store.apipurchases.integration.dto.ContentDto;
import com.store.apipurchases.integration.dto.ContentStatus;
import com.store.apipurchases.integration.service.ContentService;
import com.store.apipurchases.model.dto.PurchasePostDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ContentValidValidation implements PostPurchaseValidation{

    private ContentService contentService;

    public ContentValidValidation(ContentService contentService) {
        this.contentService = contentService;
    }

    @Override
    public void validate(String userId, PurchasePostDto purchaseDto) {
        ContentDto content = contentService.findById(purchaseDto.getContentId());
        if(content == null || content.getStatus() == ContentStatus.INACTIVE) {
            throw new CryptoStoreException(HttpStatus.UNPROCESSABLE_ENTITY, "Conteudo inválido");
        }
    }
}
