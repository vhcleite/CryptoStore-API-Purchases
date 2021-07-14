package com.store.apipurchases.model;

import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    private String userId;
    private Long contentId;
    private Double value;
    private LocalDateTime creationTimestamp;
    private LocalDateTime expirationTimestamp;
    private LocalDateTime paymentTimestamp;

    public PurchaseEntity() {
    }

    public PurchaseEntity(Long purchaseId,
                          String userId,
                          Long contentId,
                          Double value,
                          LocalDateTime creationTimestamp,
                          LocalDateTime expirationTimestamp,
                          LocalDateTime paymentTimestamp) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.contentId = contentId;
        this.value = value;
        this.creationTimestamp = creationTimestamp;
        this.expirationTimestamp = expirationTimestamp;
        this.paymentTimestamp = paymentTimestamp;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public LocalDateTime getPaymentTimestamp() {
        return paymentTimestamp;
    }

    public void setPaymentTimestamp(LocalDateTime paymentTimestamp) {
        this.paymentTimestamp = paymentTimestamp;
    }

    public LocalDateTime getExpirationTimestamp() {
        return expirationTimestamp;
    }

    public void setExpirationTimestamp(LocalDateTime expirationTimestamp) {
        this.expirationTimestamp = expirationTimestamp;
    }

    @Override
    public String toString() {
        return "PurchaseEntity{" +
                "purchaseId=" + purchaseId +
                ", userId='" + userId + '\'' +
                ", contentId=" + contentId +
                ", value=" + value +
                ", creationTimestamp=" + creationTimestamp +
                ", expirationTimestamp=" + expirationTimestamp +
                ", paymentTimestamp=" + paymentTimestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseEntity that = (PurchaseEntity) o;
        return Objects.equals(getPurchaseId(), that.getPurchaseId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPurchaseId());
    }
}
