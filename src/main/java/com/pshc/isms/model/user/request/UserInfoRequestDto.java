package com.pshc.isms.model.user.request;

public class UserInfoRequestDto {
    private String userLoginId;

    public UserInfoRequestDto() {
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }
}
