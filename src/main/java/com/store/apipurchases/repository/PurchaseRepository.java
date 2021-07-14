package com.store.apipurchases.repository;

import com.store.apipurchases.model.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    public List<PurchaseEntity> findByUserId(String userId);
}
