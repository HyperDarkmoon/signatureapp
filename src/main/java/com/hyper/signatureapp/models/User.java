package com.hyper.signatureapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] signature;
    private String firstName;
    private String lastName;
    private String phone;
    private String idCard;
    private String address;
    private String offer;
    private String item;
    private String dob;
    private String date;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] idCardFront;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] idCardBack;

    public byte[] getidCardBack() {
        return idCardBack;
    }

    public void setidCardBack(byte[] idCardBack) {
        this.idCardBack = idCardBack;
    }
    

    public byte[] getidCardFront() {
        return idCardFront;
    }

    public void setidCardFront(byte[] idCardFront) {
        this.idCardFront = idCardFront;
    }

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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }
}
