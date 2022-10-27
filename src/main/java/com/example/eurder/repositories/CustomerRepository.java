package com.example.eurder.repositories;

import com.example.eurder.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
@Repository
public class CustomerRepository {
    private ConcurrentHashMap<String, Customer> customerDatabase;

    public CustomerRepository() {
        customerDatabase = new ConcurrentHashMap<>();
    }
    public void save(Customer customer) {
        customerDatabase.put(customer.getEmailAdress(), customer);
    }

    public ConcurrentHashMap<String, Customer> getCustomerDatabase() {
        return customerDatabase;
    }
}
