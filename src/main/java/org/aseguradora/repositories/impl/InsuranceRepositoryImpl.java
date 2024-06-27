package org.aseguradora.repositories.impl;

import org.aseguradora.entity.Insurance;
import org.aseguradora.repositories.InsuranceRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class InsuranceRepositoryImpl implements InsuranceRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public InsuranceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Insurance> findAll() {
        String hql = "SELECT i FROM Insurance i";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Insurance findById(Long id) {
        String hql = "SELECT i FROM Insurance i WHERE i.id=?1";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, id);

        return (Insurance) query.getSingleResult();
    }
}
