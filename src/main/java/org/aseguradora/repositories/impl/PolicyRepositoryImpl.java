package org.aseguradora.repositories.impl;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Policy;
import org.aseguradora.entity.dto.PolicyDto;
import org.aseguradora.repositories.PolicyRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PolicyRepositoryImpl implements PolicyRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public PolicyRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Policy> findAll() {

        String hql = "SELECT p FROM Policy p";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        return query.getResultList();
    }


    @Override // este busca por cliente
    @Transactional
    public List<Policy> findByCustomerId(Long id) {
        String hql = "SELECT p FROM Policy p WHERE p.customer.id =?1";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, id);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Policy findById(Long id) {
        String hql = "SELECT p FROM Policy p WHERE p.id=?1";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, id);

        return (Policy) query.getSingleResult();
    }


    @Transactional
    @Override
    public void save(Policy policy) {
        this.sessionFactory.getCurrentSession().save(policy);
    }
}



