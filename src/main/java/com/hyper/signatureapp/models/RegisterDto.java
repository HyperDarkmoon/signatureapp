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
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String idCard;
    @NotEmpty
    private String address;
    @NotEmpty
    private String offer;

    @NotEmpty
    private String item;
    @NotEmpty
    private String dob;
    @NotEmpty
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public @NotEmpty String getOffer() {
        return offer;
    }

    public void setOffer(@NotEmpty String offer) {
        this.offer = offer;
    }

    public @NotEmpty String getItem() {
        return item;
    }

    public void setItem(@NotEmpty String item) {
        this.item = item;
    }

    public @NotEmpty String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty String lastName) {
        this.lastName = lastName;
    }

    public @NotEmpty String getPhone() {
        return phone;
    }

    public void setPhone(@NotEmpty String phone) {
        this.phone = phone;
    }

    public @NotEmpty String getIdCard() {
        return idCard;
    }

    public void setIdCard(@NotEmpty String idCard) {
        this.idCard = idCard;
    }

    public @NotEmpty String getAddress() {
        return address;
    }

    public void setAddress(@NotEmpty String address) {
        this.address = address;
    }

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
