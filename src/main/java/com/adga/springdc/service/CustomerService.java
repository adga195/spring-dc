package com.adga.springdc.service;

import com.adga.springdc.entity.Customer;

import java.util.List;

public interface CustomerService {
	
	List<Customer> getCustomers();
	
	Customer getCustomer(int id);
	
	int saveCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomer(Customer customer);

}
