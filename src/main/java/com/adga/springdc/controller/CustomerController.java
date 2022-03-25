package com.adga.springdc.controller;

import com.adga.springdc.entity.Customer;
import com.adga.springdc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // Please get used to use the QUALIFIER even if
    // there is only one implementation of the interface
    // Spring will autowire CustomerDAO/CustomerService because @Repository
    // enables component scanning.
    @Autowired
    @Qualifier("customerServiceImpl")
    private CustomerService customerService;

    // Only handles GET requests
    @GetMapping("/list")
    public String listCustomers(Model model) {

        // Get customers from the DAO
        List<Customer> customers = customerService.getCustomers();

        // Add customers to the model
        model.addAttribute("customers", customers);

        return "list-customers";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "customer-form";

    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        int customerId = customerService.saveCustomer(customer);

        System.out.println("Customer with ID = " + customerId + " assigned/saved..");

        return "redirect:/customer/list";
    }

    // @RequestParam allows to retrieve the parameter used in the link
    // and also convert it
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model) {

        Customer customer = customerService.getCustomer(customerId);

        model.addAttribute("customer", customer);

        return "customer-form-update";

    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {

        customerService.updateCustomer(customer);

        System.out.println("Customer with ID = " + customer.getId() + " updated..");

        return "redirect:/customer/list";

    }

    // @RequestParam allows to retrieve the parameter used in the link
    // and also convert it
    @GetMapping("/showFormForDelete")
    public String showFormForDelete(@RequestParam("customerId") int customerId, Model model) {

        Customer customer = customerService.getCustomer(customerId);

        model.addAttribute("customer", customer);

        return "customer-form-delete";

    }

    @PostMapping("/deleteCustomer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {

        customerService.deleteCustomer(customer);

        System.out.println("Customer with ID = " + customer.getId() + " deleted..");

        return "redirect:/customer/list";

    }

}
