package com.store.apipurchases.service;

import com.store.apipurchases.exceptions.CryptoStoreException;
import com.store.apipurchases.model.PaymentStatus;
import com.store.apipurchases.model.PurchaseEntity;
import com.store.apipurchases.model.dto.PurchaseGetDto;
import com.store.apipurchases.model.dto.PurchasePostDto;
import com.store.apipurchases.model.dto.PurchasePutDto;
import com.store.apipurchases.repository.PurchaseRepository;
import com.store.apipurchases.validations.postPurchase.PostPurchaseValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    private static final ModelMapper modelMapper = new ModelMapper();
    private final PurchaseRepository purchaseRepository;

    @Value("${payment.expiration.days}")
    public Long expirationDays;

    private final List<PostPurchaseValidation> validations;

    public PurchaseService(PurchaseRepository purchaseRepository, List<PostPurchaseValidation> validations) {
        this.purchaseRepository = purchaseRepository;
        this.validations = validations;
    }

    public List<PurchaseGetDto> getPurchases(String userId) {
        List<PurchaseEntity> purchases = purchaseRepository.findByUserId(userId);
        if(CollectionUtils.isEmpty(purchases)) {
            throw new CryptoStoreException(HttpStatus.NO_CONTENT, "Usuário não efetuou compras ainda");
        }
        return purchases.stream().map(p -> modelMapper.map(p, PurchaseGetDto.class)).collect(Collectors.toList());
    }

    public PurchaseGetDto create(String userId, PurchasePostDto purchaseDto) {
        LocalDateTime now = LocalDateTime.now();

        validations.forEach(v -> v.validate(userId, purchaseDto));

        PurchaseEntity purchase = modelMapper.map(purchaseDto, PurchaseEntity.class);
        purchase.setPurchaseId(null);
        purchase.setUserId(userId);
        purchase.setCreationTimestamp(now);
        purchase.setExpirationTimestamp(now.plus(expirationDays, ChronoUnit.DAYS));
        return modelMapper.map(purchaseRepository.save(purchase), PurchaseGetDto.class);
    }

    public PurchaseEntity findOpenPurchasesBy(String userId, Long contentId) {
        return purchaseRepository.findByUserIdAndContentIdAndPaymentTimestampIsNullAndExpirationTimestampGreaterThan(userId, contentId, LocalDateTime.now());
    }

    public List<PurchaseGetDto> getPurchases(PaymentStatus status) {
        List<PurchaseEntity> purchases = new ArrayList<>();
        if(status == PaymentStatus.PENDING_PAYMENT) {
            purchases = purchaseRepository.findByExpirationTimestampAfterAndPaymentTimestampIsNull(LocalDateTime.now());
        } else {
            throw new CryptoStoreException(HttpStatus.NOT_IMPLEMENTED, "Busca não implementada");
        }

        if(CollectionUtils.isEmpty(purchases)) {
            throw new CryptoStoreException(HttpStatus.NO_CONTENT, "Sem compras pendentes");
        }
        return purchases.stream().map(p -> modelMapper.map(p, PurchaseGetDto.class)).collect(Collectors.toList());
    }

    public PurchaseEntity update(Long purchaseId, String userId, PurchasePutDto purchase) {
        Optional<PurchaseEntity> purchaseOptional = purchaseRepository.findById(purchaseId);
        PurchaseEntity purchaseEntity = purchaseOptional.orElseThrow(() -> new CryptoStoreException(HttpStatus.UNPROCESSABLE_ENTITY, "Compra não encontrada"));
        purchaseEntity.setPaymentTimestamp(purchase.getPaymentTimestamp());
        purchaseRepository.save(purchaseEntity);
        return purchaseEntity;
    }
}
