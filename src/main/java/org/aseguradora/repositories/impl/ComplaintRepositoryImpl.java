package org.aseguradora.repositories.impl;

import org.aseguradora.entity.Complaint;
import org.aseguradora.repositories.ComplaintRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComplaintRepositoryImpl implements ComplaintRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public ComplaintRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Complaint> findAll() {
        return this.sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Complaint c")
                .getResultList();
    }

    @Override
    public Complaint findOne(Long id) {
        return (Complaint) this.sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Complaint c WHERE c.id= :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Complaint> findByCustomerId(Long id) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Complaint c WHERE c.customer.id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void save(Complaint complaint) {
        this.sessionFactory.getCurrentSession().save(complaint);
    }

    @Override
    public void update(Complaint complaint) {
        this.sessionFactory.getCurrentSession().update(complaint);
    }
}
