package com.store.apipurchases.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    @JsonProperty("id")
    private String userId;
    private String name;
    private String raidenAccount;

    public UserDto() {
    }

    public UserDto(String userId, String name, String raidenAccount) {
        this.userId = userId;
        this.name = name;
        this.raidenAccount = raidenAccount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRaidenAccount() {
        return raidenAccount;
    }

    public void setRaidenAccount(String raidenAccount) {
        this.raidenAccount = raidenAccount;
    }


}
