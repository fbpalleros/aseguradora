package org.aseguradora.repositories.impl;

import org.aseguradora.repositories.LiveRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class LiveRepositoryImpl implements LiveRepository {

    private SessionFactory sessionFactory;

    @Autowired //llama la base de datos
    public LiveRepositoryImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<String> listarTodasLosOficios() {
        String hql = "SELECT DISTINCT c.oficio FROM Live c";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        return query.getResultList();
    }


    @Override
    @Transactional
    public List<Integer> buscarAnioPorOficio(String oficio) {
        String hql = "SELECT DISTINCT c.anio FROM Live c where c.oficio=?1";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, oficio);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Double buscarPrecioPorAnioYOficio(String oficio , Integer anio ) {
        String hql = "SELECT DISTINCT  c.precio FROM Live c WHERE c.oficio=?1 and c.anio=?2"  ;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, oficio);
        query.setParameter(2, anio);


        return (Double) query.getSingleResult();

    }


}
