package com.hyper.signatureapp.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class RegisterDto {
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 6, message="Password must be at least 6 characters long")
    private String password;
    @NotEmpty
    private String email;
    @NotEmpty
    private byte[] signature;

    public @NotEmpty String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty String username) {
        this.username = username;
    }

    public @NotEmpty @Size(min = 6, message="Password must be at least 6 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty @Size(min = 6, message="Password must be at least 6 characters long") String password) {
        this.password = password;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    public @NotEmpty byte[] getSignature() {
        return signature;
    }

    public void setSignature(@NotEmpty byte[] signature) {
        this.signature = signature;
    }
}
