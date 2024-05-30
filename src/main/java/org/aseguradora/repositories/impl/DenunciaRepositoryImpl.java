package org.aseguradora.repositories.impl;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.aseguradora.entity.Customer;
import org.aseguradora.entity.Denuncia;
import org.aseguradora.repositories.DenunciaRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DenunciaRepositoryImpl implements DenunciaRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public DenunciaRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    @Transactional
	public Denuncia findById(Long id) {
        String hql = "SELECT c FROM Denuncia c WHERE c.id=?1";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, id);
        return (Denuncia) query.getSingleResult();
	}

	@Override
	@Transactional
	public List<Denuncia> findAll() {
        String hql = "SELECT p FROM Denuncia p";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
	}

	@Override
	@Transactional
	public List<Denuncia> findAllByCustomerId(Long customerId) {
		String hql = "SELECT d FROM Denuncia d JOIN Policy p ON p.id = d.policy.id WHERE p.customer.id = : customer_id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("customer_id", customerId);
        return query.getResultList();
	}
	
    @Override
    @Transactional
    public void save(Denuncia denuncia) {
        this.sessionFactory.getCurrentSession().save(denuncia);
    }
}
