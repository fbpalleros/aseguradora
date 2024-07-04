package org.aseguradora.repositories.impl;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.CustomerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CustomerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
    public List<Policy> findPoliciesByIdCustomer(Long id) {
        String hql = "SELECT p FROM Customer c JOIN Policy p ON c.id = p.customer.id WHERE p.customer.id = : customer_id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("customer_id", id);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        this.sessionFactory.getCurrentSession().save(customer);
    }

    @Override
    @Transactional
    public Customer findNameCustumer(String email, String password) {
        final Session session = sessionFactory.getCurrentSession();

       return (Customer) session.createCriteria(Customer.class)
               .add(Restrictions.eq("email", email)) //buscador
                .add(Restrictions.eq("password", password)) //buscador
               .uniqueResult();

    }

    @Override
    @Transactional
    public void actualizar(Customer custumer) {
//        this.sessionFactory.getCurrentSession().saveOrUpdate(item);
        String hql = "UPDATE Customer set name = :name, email = :email WHERE id = :id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("name", custumer.getName());
        query.setParameter("email", custumer.getEmail());
        query.setParameter("id", custumer.getId());
        query.executeUpdate(); // Sirve tambien para delete
    }

    @Override
    @Transactional
    public Policy findPolicyByIdCustomer(Long idCustomer, Long idPolicy) {
        List<Policy> policies = findPoliciesByIdCustomer(idCustomer);
        Stream<Policy> policyStream = policies.stream().filter(policy -> policy.getId().equals(idPolicy));
        Optional<Policy> policy = policyStream.findFirst();
        return policy.get();
    }

}


