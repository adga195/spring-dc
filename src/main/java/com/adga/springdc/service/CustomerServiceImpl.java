package com.adga.springdc.service;

import com.adga.springdc.dao.CustomerDAO;
import com.adga.springdc.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service handles services exceptions
@Service
@Component("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	@Qualifier("customerDAOImpl")
	private CustomerDAO customerDAO;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		System.out.println("CustomerService - getCustomers()");
		
		List<Customer> customers = customerDAO.getCustomers();

		return customers;
		
	}
	
	@Override
	@Transactional
	public Customer getCustomer(int id) {
		
		System.out.println("CustomerService - getCustomer()");
		
		Optional<Customer> customer = customerDAO.getCustomer(id);

		return customer.orElseGet(() -> new Customer());

	}
	
	@Override
	@Transactional
	public int saveCustomer(Customer customer) {
		
		System.out.println("CustomerService - saveCustomer()");
		
		return customerDAO.saveCustomer(customer);
				
	}
	
	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		
		System.out.println("CustomerService - updateCustomer()");
		
		customerDAO.updateCustomer(customer);
		
	}
	
	@Override
	@Transactional
	public void deleteCustomer(Customer customer) {
		
		System.out.println("CustomerService - updateCustomer()");
		
		customerDAO.deleteCustomer(customer);
		
	}

}
