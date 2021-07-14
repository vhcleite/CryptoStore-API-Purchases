package com.store.apipurchases.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import java.time.LocalDateTime;

public class PurchasePostDto {
    @NotNull
    @JsonProperty("content_id")
    private Long contentId;

    @NotNull
    @JsonProperty("value")
    private Double value;

    public PurchasePostDto() {
    }

    public PurchasePostDto(Long contentId, Double value) {
        this.contentId = contentId;
        this.value = value;
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
}
