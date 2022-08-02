package com.sapo.ex8_spring_security.model.dto.respond;

public class LoginRespond {
    String token;

    public LoginRespond(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
