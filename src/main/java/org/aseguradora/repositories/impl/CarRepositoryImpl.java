package org.aseguradora.repositories.impl;

import org.aseguradora.entity.Car;
import org.aseguradora.repositories.CarRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private SessionFactory sessionFactory; //llama la base de datos

    @Autowired //llama la base de datos
    public CarRepositoryImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional //bloquea todas las otras operaciones mientras que esta se realiza (arregla el bucle infinito)
    public List<Car> findAll() {

        String hql = "SELECT c FROM Car c";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        return query.getResultList();
    }

    @Override
    @Transactional
    public List<String> findDistinctName() {
        String hql = "SELECT DISTINCT c.name FROM Car c";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        return query.getResultList();
    }

    @Override
    @Transactional
    public List<String> findByModel() {
        String hql = "SELECT DISTINCT c.model FROM Car c";

        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<String> findByModelWithParameter(String name) { //este busca modelos dependiendo de la marca
        String hql = "SELECT DISTINCT c.model FROM Car c where c.name=?1";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, name);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Integer> findDistinctByNameAndModel(String name , String model ) {
        String hql = "SELECT DISTINCT c.anio FROM Car c WHERE c.name=?1 and c.model=?2"  ;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, name);
        query.setParameter(2, model);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Double findPrice(String name , String model , Integer year ) {
        String hql = "SELECT DISTINCT  c.precio FROM Car c WHERE c.name=?1 and c.model=?2 and c.anio=?3"  ;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, name);
        query.setParameter(2, model);
        query.setParameter(3, year);

        return (Double) query.getSingleResult();

    }

}
