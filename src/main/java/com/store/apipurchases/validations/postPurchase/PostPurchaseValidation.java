package com.store.apipurchases.validations.postPurchase;

import com.store.apipurchases.model.dto.PurchasePostDto;

public interface PostPurchaseValidation {

    public void validate(String userId, PurchasePostDto purchaseDto);
}
