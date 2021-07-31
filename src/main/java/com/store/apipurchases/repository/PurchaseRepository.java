package com.store.apipurchases.repository;

import com.store.apipurchases.model.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    public List<PurchaseEntity> findByUserId(String userId);

    public PurchaseEntity findByUserIdAndContentIdAndPaymentTimestampIsNullAndExpirationTimestampGreaterThan(String userId, Long contentId, LocalDateTime now);

    public List<PurchaseEntity> findByExpirationTimestampAfterAndPaymentTimestampIsNull(LocalDateTime now);
}
