package org.alkemy.challenge1.authentication;

public class Response {
    private String token;

    public String getToken() {
        return token;
    }

    public Response(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
