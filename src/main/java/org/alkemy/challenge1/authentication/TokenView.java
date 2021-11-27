package org.alkemy.challenge1.authentication;

public class TokenView {
    private String token;
    private String tokenType = "Bearer";

    public String getTokenType() {
        return tokenType;
    }

    public String getToken() {
        return token;
    }

    public TokenView(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
