package com.store.apipurchases.controller;

import com.store.apipurchases.model.dto.PurchasePutDto;
import com.store.apipurchases.model.PaymentStatus;
import com.store.apipurchases.model.PurchaseEntity;
import com.store.apipurchases.model.dto.PurchaseGetDto;
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
@RequestMapping("store/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchase")
    public ResponseEntity<List<PurchaseGetDto>> getByStatus(@RequestParam("status") PaymentStatus status) {
        return new ResponseEntity<>(purchaseService.getPurchases(status), HttpStatus.OK);
    }

    @GetMapping("users/{userId}/purchase")
    public ResponseEntity<List<PurchaseGetDto>> getByUserId(@PathVariable(name = "userId") @NotNull String UserId) {
        return new ResponseEntity<>(purchaseService.getPurchases(UserId), HttpStatus.OK);
    }

    @PostMapping("users/{userId}/purchase")
    public ResponseEntity<PurchaseGetDto> create(@PathVariable(name =  "userId") @NotNull String userId,
                                                 @RequestBody @Validated PurchasePostDto purchase) {
        PurchaseGetDto purchaseDto = purchaseService.create(userId, purchase);
        return new ResponseEntity<>(purchaseDto, HttpStatus.CREATED);
    }

    @PutMapping("users/{userId}/purchase/{purchaseId}")
    public ResponseEntity<PurchaseEntity> create(@PathVariable(name =  "userId") @NotNull String userId,
                                                 @PathVariable(name = "purchaseId") @NotNull Long purchaseId,
                                                 @RequestBody @Validated PurchasePutDto purchase) {
        PurchaseEntity purchaseEntity = purchaseService.update(purchaseId, userId, purchase);
        return new ResponseEntity<>(purchaseEntity, HttpStatus.CREATED);
    }
}
