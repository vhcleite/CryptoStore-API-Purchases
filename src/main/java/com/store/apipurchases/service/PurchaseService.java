package com.store.apipurchases.service;

import com.store.apipurchases.exceptions.CryptoStoreException;
import com.store.apipurchases.integration.service.UserService;
import com.store.apipurchases.model.PurchaseEntity;
import com.store.apipurchases.model.dto.PurchasePostDto;
import com.store.apipurchases.repository.PurchaseRepository;
import com.store.apipurchases.validations.postPurchase.PostPurchaseValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PurchaseService {

    private static final ModelMapper modelMapper = new ModelMapper();
    private final PurchaseRepository purchaseRepository;

    @Value("${payment.expiration.days}")
    public Long expirationDays;

    private List<PostPurchaseValidation> validations;

    public PurchaseService(PurchaseRepository purchaseRepository, List<PostPurchaseValidation> validations) {
        this.purchaseRepository = purchaseRepository;
        this.validations = validations;
    }

    public List<PurchaseEntity> getPurchases(String userId) {
        List<PurchaseEntity> purchases = purchaseRepository.findByUserId(userId);
        if(CollectionUtils.isEmpty(purchases)) {
            throw new CryptoStoreException(HttpStatus.NO_CONTENT, "Usuário não efetuou compras ainda");
        }
        return purchases;
    }

    public PurchaseEntity create(String userId, PurchasePostDto purchaseDto) {
        LocalDateTime now = LocalDateTime.now();

        validations.forEach(v -> v.validate(userId, purchaseDto));
        // verificar se compra é duplicadas

        PurchaseEntity purchase = modelMapper.map(purchaseDto, PurchaseEntity.class);
        purchase.setPurchaseId(null);
        purchase.setUserId(userId);
        purchase.setCreationTimestamp(now);
        purchase.setExpirationTimestamp(now.plus(expirationDays, ChronoUnit.DAYS));
        return purchaseRepository.save(purchase);
    }

    public PurchaseEntity findOpenPurchasesBy(String userId, Long contentId) {
        return purchaseRepository.findByUserIdAndContentIdAndPaymentTimestampIsNullAndExpirationTimestampGreaterThan(userId, contentId, LocalDateTime.now());
    }
}
