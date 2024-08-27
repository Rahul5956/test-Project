package com.app.dto;

import java.io.Serializable;

public class NgoDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String contactInfo;
    private String address;
    private String beneficiary; // Adjusted to match the entity
    private String sectionLicence;
    private Integer registrationNumber;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getSectionLicence() {
        return sectionLicence;
    }

    public void setSectionLicence(String sectionLicence) {
        this.sectionLicence = sectionLicence;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
