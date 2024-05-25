package org.aseguradora.repositories.impl;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.CustomerRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CustomerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional
    public List<Customer> findAll() {
        String hql = "SELECT c FROM Customer c";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Customer findOne(Long id) {
        String hql = "SELECT c FROM Customer c WHERE c.id=?1";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, id);
        return (Customer) query.getSingleResult();
    }

    @Override
    @Transactional
    public Customer findByEmail(String email) {
        String hql = "SELECT c FROM Customer c WHERE c.email=?1";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, email);
        return (Customer) query.getSingleResult();
    }

    @Override
    @Transactional
    public List<Policy> findPoliciesByIdCustomer(Long id) {
        String hql = "SELECT c.name, p.insurance FROM Customer c JOIN Policy p ON c.id = p.customer.id WHERE p.customer.id = : customer_id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("customer_id", id);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        this.sessionFactory.getCurrentSession().save(customer);
    }
}
