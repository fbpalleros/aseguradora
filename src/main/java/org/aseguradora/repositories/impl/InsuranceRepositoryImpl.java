package org.aseguradora.repositories.impl;

import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.InsuranceType;
import org.aseguradora.repositories.InsuranceRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InsuranceRepositoryImpl implements InsuranceRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public InsuranceRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    List<Insurance> insurances = new ArrayList<>();


    @Override
    public List<Insurance> findAll() {
        Insurance i1 = new Insurance(1L, "Facundo", InsuranceType.PERSONA, 2000);
        Insurance i2 = new Insurance(2L, "Ana", InsuranceType.PERSONA, 5000);
        Insurance i3 = new Insurance(3L, "Miguel", InsuranceType.AUTOMOTOR, 22000);
        Insurance i4 = new Insurance(4L, "Miguel", InsuranceType.HOGAR, 22000);

        insurances.add(i1);
        insurances.add(i2);
        insurances.add(i3);
        insurances.add(i4);
        return insurances;
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
