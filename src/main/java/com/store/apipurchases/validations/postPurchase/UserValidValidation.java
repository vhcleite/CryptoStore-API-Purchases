package com.store.apipurchases.validations.postPurchase;

import com.store.apipurchases.exceptions.CryptoStoreException;
import com.store.apipurchases.integration.service.UserService;
import com.store.apipurchases.integration.dto.UserDto;
import com.store.apipurchases.model.dto.PurchasePostDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserValidValidation implements PostPurchaseValidation {

    private UserService userService;

    public UserValidValidation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void validate(String userId, PurchasePostDto purchaseDto) {
        // verificar se o userId é válido
        UserDto userDto = userService.getUserById(userId);
        if(userDto == null) {
            throw new CryptoStoreException(HttpStatus.UNPROCESSABLE_ENTITY, "Usuário inválido");
        }
    }
}
