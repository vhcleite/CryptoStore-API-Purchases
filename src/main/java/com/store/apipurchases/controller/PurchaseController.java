package com.store.apipurchases.controller;

import com.store.apipurchases.model.PurchaseEntity;
import com.store.apipurchases.model.dto.PurchasePostDto;
import com.store.apipurchases.service.PurchaseService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("store/v1/users")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/{userId}/purchase")
    public ResponseEntity<List<PurchaseEntity>> getByUserId(@PathVariable(name = "userId") @NotNull String UserId) {
        return new ResponseEntity<>(purchaseService.getPurchases(UserId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/purchase")
    public ResponseEntity<PurchaseEntity> create(@PathVariable(name =  "userId") @NotNull String userId,
                                                 @RequestBody @Validated PurchasePostDto purchase) {
        PurchaseEntity purchaseEntity = purchaseService.create(userId, purchase);
        return new ResponseEntity<>(purchaseEntity, HttpStatus.CREATED);
    }
}
