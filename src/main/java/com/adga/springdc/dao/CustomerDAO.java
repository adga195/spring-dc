package com.adga.springdc.dao;

import com.adga.springdc.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {

    List<Customer> getCustomers();

    Optional<Customer> getCustomer(int id);

    int saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);

}
