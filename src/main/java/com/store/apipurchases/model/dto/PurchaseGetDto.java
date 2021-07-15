package com.store.apipurchases.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.store.apipurchases.model.PaymentStatus;
import com.sun.istack.NotNull;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class PurchaseGetDto {

    @JsonProperty("id_compra")
    private Long purchaseId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("content_id")
    private Long contentId;

    @JsonProperty("value")
    private Double value;

    @JsonProperty("data_hora_compra")
    private LocalDateTime creationTimestamp;

    @JsonProperty("data_hora_vencimento_pagamento")
    private LocalDateTime expirationTimestamp;

    @JsonProperty("data_hora_pagamento")
    private LocalDateTime paymentTimestamp;

    public PurchaseGetDto() {
    }

    public PurchaseGetDto(Long purchaseId, String userId, Long contentId, Double value, LocalDateTime creationTimestamp, LocalDateTime expirationTimestamp, LocalDateTime paymentTimestamp) {
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

    public LocalDateTime getExpirationTimestamp() {
        return expirationTimestamp;
    }

    public void setExpirationTimestamp(LocalDateTime expirationTimestamp) {
        this.expirationTimestamp = expirationTimestamp;
    }

    public LocalDateTime getPaymentTimestamp() {
        return paymentTimestamp;
    }

    public void setPaymentTimestamp(LocalDateTime paymentTimestamp) {
        this.paymentTimestamp = paymentTimestamp;
    }

    @JsonProperty("status_pagamento")
    public PaymentStatus getStatus() {
        LocalDateTime now = LocalDateTime.now();

        if(getPaymentTimestamp() != null) {
            return PaymentStatus.PAYMENT_MADE;
        } else {
            if(now.isBefore(getExpirationTimestamp())) {
                return PaymentStatus.PENDING_PAYMENT;
            } else {
                return PaymentStatus.PAYMENT_NOT_MADE;
            }
        }
    }

}
