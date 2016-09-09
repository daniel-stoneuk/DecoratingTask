package com.danielstone;

public class Customer {

    private int customerId = -1;
    private String firstName ="";
    private String surname = "";
    private String telephoneNumber = "";

    public Customer(int customerId, String firstName, String surname, String telephoneNumber) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
    }

    public Customer() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
