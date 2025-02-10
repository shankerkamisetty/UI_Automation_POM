package com.ui.pojo;

public class Address {
    private String company;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipcode;
    private String homePhoneNumber;
    private String mobileNumber;
    private String additionalInformation;
    private String aliasAddress;

    public Address(String company, String addressLine1, String addressLine2, String city, String state,
                   String zipcode, String homePhoneNumber, String mobileNumber, String additionalInformation, String aliasAddress) {
        this.company = company;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.homePhoneNumber = homePhoneNumber;
        this.mobileNumber = mobileNumber;
        this.additionalInformation = additionalInformation;
        this.aliasAddress = aliasAddress;
    }

    public String getCompany() {
        return company;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public String getAliasAddress() {
        return aliasAddress;
    }

}
