package org.aseguradora.repositories.impl;

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

    @Override
    @Transactional
    public List<PolicyDto> findAllDto() {
//        String hql = "SELECT i.insuranceType, p.coverage, p.expiration FROM Policy p LEFT JOIN Insurance i ON p.insuranceId =?1"; //hay problemas con el cast, hibernate no puede juntar Insurance con Long
//        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
//        query.setParameter(1, 3L);

        return List.of();
    }

    @Override
    @Transactional
    public List<Policy> findById(Long id) {
        String hql = "SELECT p FROM Policy p WHERE cast(p.customerId as long)=?1";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, id);

        return query.getResultList();
    }


    @Override
    @Transactional
    public Object findInsuranceType(){
        String hql = "SELECT p.insuranceId FROM Policy p where p.id=?1";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, 4L);

        return query.getSingleResult();
    }
}
