package org.aseguradora.repositories.impl;

import org.aseguradora.entity.Payment;
import org.aseguradora.repositories.PaymentRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public PaymentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Payment> findAll() {
        String hql = "SELECT p FROM Payment p";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        return query.getResultList();
    }

    @Override
    public void save(Payment payment) {
        this.sessionFactory.getCurrentSession().save(payment);
    }

    @Override
    public List<Payment> findByCustomerId(Long customerId) {
        String hql = "SELECT p FROM Payment p WHERE p.customer.id = : customer_id ";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("customer_id", customerId);

        return query.getResultList();
    }
}
