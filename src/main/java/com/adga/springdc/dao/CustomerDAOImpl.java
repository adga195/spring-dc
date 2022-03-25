package com.adga.springdc.dao;

import com.adga.springdc.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// @Repository handles all JDBC exceptions, no need of try-catch
@Repository
@Component("customerDAOImpl")
public class CustomerDAOImpl implements CustomerDAO {

    // We are injecting the sessionFactory bean
    // using the custom bean name for the session factory
    @Autowired
    @Qualifier("sqlServerSessionFactory")
    private SessionFactory sessionFactory;

    // @Transactional eliminates the need of beginning and
    // Committing every session transaction
    @Override
    public List<Customer> getCustomers() {

        System.out.println("CustomerDAO - getCustomers()");

        Session currentSession = sessionFactory
                .getCurrentSession();

        Query<Customer> query =
                currentSession
                        .createQuery("from Customer", Customer.class);

        List<Customer> customers = query.getResultList();

		return customers;

    }

    public Optional<Customer> getCustomer(int id) {

        System.out.println("CustomerDAO - getCustomer()");

        Session currentSession = sessionFactory
                .getCurrentSession();
		
		/*
				
		Query<Customer> query =
				currentSession
				.createQuery("from Customer c where c.id = :id", Customer.class);
		
		query.setParameter("id", id);
		
		Customer customer = query.getSingleResult();
		
		*/

        Customer customer = currentSession.get(Customer.class, id);

        return Optional.ofNullable(customer);
    }

    // saveCustomer && updateCustomer can be merged into a single method

    @Override
    public int saveCustomer(Customer customer) {

        System.out.println("CustomerDAO - saveCustomer()");

        Session currentSession = sessionFactory
                .getCurrentSession();

        // currentSession.save(customer); // Hibernate can figure out
        // if save or update given the entity object id
        currentSession.saveOrUpdate(customer);

        return customer.getId();

    }

    @Override
    public void updateCustomer(Customer customer) {

        System.out.println("CustomerDAO - updateCustomer()");

        Session currentSession = sessionFactory
                .getCurrentSession();

        // currentSession.update(customer); // Hibernate can figure out
        // if save or update given the entity object id
        currentSession.saveOrUpdate(customer);

    }

    @Override
    public void deleteCustomer(Customer customer) {

        System.out.println("CustomerDAO - deleteCustomer()");

        Session currentSession = sessionFactory
                .getCurrentSession();

        currentSession.delete(customer);

    }

}
