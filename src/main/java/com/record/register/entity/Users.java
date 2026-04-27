package com.record.register.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Users extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String email;

    private String mobileNumber;

    private String password;

    private String photoUrl;

    private String role;

    private String status;

    private LocalDateTime lastLoginAt;

    private Long deletedBy;

    private LocalDateTime deletedAt;

    @Version
    private Long version;

    private Boolean isDeleted;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }


    public Long getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Users(Long userId, String name, String email, String mobileNumber, String password, String photoUrl, String role, String status, LocalDateTime lastLoginAt, Long deletedBy, LocalDateTime deletedAt, Long version, Boolean isDeleted) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.photoUrl = photoUrl;
        this.role = role;
        this.status = status;
        this.lastLoginAt = lastLoginAt;
        this.deletedBy = deletedBy;
        this.deletedAt = deletedAt;
        this.version = version;
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", lastLoginAt=" + lastLoginAt +
                ", deletedBy=" + deletedBy +
                ", deletedAt=" + deletedAt +
                ", version=" + version +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public Users(){}
}
