package com.record.register.dto;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {

    private String name;

    private String email;

    private String mobileNumber;

    private String password;

    private MultipartFile photoUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(MultipartFile photoUrl) {
        this.photoUrl = photoUrl;
    }

    public UserDto(String name, String email, String mobileNumber, String password, MultipartFile photoUrl) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }

    public UserDto(){}
}
