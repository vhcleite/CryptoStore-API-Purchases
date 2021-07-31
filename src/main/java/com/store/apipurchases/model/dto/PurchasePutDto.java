package com.store.apipurchases.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PurchasePutDto {

    @JsonProperty("data_hora_pagamento")
    private LocalDateTime paymentTimestamp;

    public PurchasePutDto() {
    }

    public PurchasePutDto(LocalDateTime paymentTimestamp) {
        this.paymentTimestamp = paymentTimestamp;
    }

    public LocalDateTime getPaymentTimestamp() {
        return paymentTimestamp;
    }

    public void setPaymentTimestamp(LocalDateTime paymentTimestamp) {
        this.paymentTimestamp = paymentTimestamp;
    }
}
