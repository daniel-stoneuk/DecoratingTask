package com.danielstone;

import java.util.ArrayList;
import java.util.List;


public class SaveFile {

    private List<Customer> customers = new ArrayList<>();

    public SaveFile() {
    }

    public SaveFile(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
