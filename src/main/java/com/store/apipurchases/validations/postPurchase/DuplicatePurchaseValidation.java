package com.store.apipurchases.validations.postPurchase;

import com.store.apipurchases.exceptions.CryptoStoreException;
import com.store.apipurchases.model.PurchaseEntity;
import com.store.apipurchases.model.dto.PurchasePostDto;
import com.store.apipurchases.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DuplicatePurchaseValidation implements PostPurchaseValidation {

    private PurchaseService purchaseService;

    public DuplicatePurchaseValidation(@Lazy PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    public void validate(String userId, PurchasePostDto purchaseDto) {
        PurchaseEntity purchase = purchaseService.findOpenPurchasesBy(userId, purchaseDto.getContentId());
        if(purchase != null) {
            throw new CryptoStoreException(HttpStatus.UNPROCESSABLE_ENTITY, "Compra esperando pagamento");
        }
    }
}
