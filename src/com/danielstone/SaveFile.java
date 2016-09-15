package com.danielstone;

import java.util.ArrayList;
import java.util.List;


public class SaveFile {

    private List<Customer> customers = new ArrayList<>();
    private List<Quote> quotes = new ArrayList<>();

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

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
