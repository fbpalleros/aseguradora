package org.aseguradora.repositories.impl;



import org.aseguradora.repositories.CityRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CityRepositoryImpl implements CityRepository {

    private SessionFactory sessionFactory;

    @Autowired //llama la base de datos
    public CityRepositoryImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<String> listarTodasLasProvincias() {
        String hql = "SELECT DISTINCT c.provincia FROM City c";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        return query.getResultList();
    }

    @Override
    @Transactional
    public List<String> buscarDependiendoLaProvincia(String provincia) { //este busca modelos dependiendo de la marca
        String hql = "SELECT DISTINCT c.localidad FROM City c where c.provincia=?1";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, provincia);
        return query.getResultList();
    }

}
