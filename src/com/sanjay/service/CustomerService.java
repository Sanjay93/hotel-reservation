package com.sanjay.service;

import com.sanjay.exceptions.InvalidDataException;
import com.sanjay.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerService {

    static ConcurrentHashMap<String, Customer> customerHashMap = new ConcurrentHashMap<>();
    private static final CustomerService instance = new CustomerService();

    private CustomerService() {

    }

    public static CustomerService getInstance() {
        return instance;
    }

    public void addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        customerHashMap.put(email, customer);
    }

    public Customer getCustomer(String email) throws InvalidDataException {
        if (customerHashMap.containsKey(email)) {
            return customerHashMap.getOrDefault(email, null);
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        if (customerHashMap.isEmpty()) {
            return customerList;
        }
        for (Map.Entry<String, Customer> entrySet : customerHashMap.entrySet()) {
            Customer value = entrySet.getValue();
            customerList.add(value);
        }
        return customerList;
    }

}
