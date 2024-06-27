package org.aseguradora.repositories;

import org.aseguradora.config.HibernateConfig;
import org.aseguradora.entity.City;
import org.aseguradora.repositories.impl.CityRepositoryImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, CityRepositoryImpl.class})
public class CityRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private CityRepository cityRepository;

    @BeforeEach
    public void init(){
        this.cityRepository = new CityRepositoryImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObtenganSinRepetirTodasLasProvincias(){
        List<String> provinciasMock = getProvincias();
        List<String> provincias = this.cityRepository.listarTodasLasProvincias();

        assertThat(provincias, equalTo(provinciasMock));
        assertThat(provincias.size(), equalTo(3));
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObtenganLasLocalidadesDeUnaProvincia(){
        List<String> localidadesMock = getLocalidadesDeProvincia();
        List<String> localidades = this.cityRepository.buscarDependiendoLaProvincia("Buenos Aires");

        assertThat(localidades, equalTo(localidadesMock));
        assertThat(localidades.size(), equalTo(2));
    }

    private List<String> getProvincias(){
        List<City> cities = new ArrayList<>();
        cities.add(new City(1L, "Buenos Aires", "Merlo"));
        cities.add(new City(2L, "Buenos Aires", "Moron"));
        cities.add(new City(3L, "Chubut", "Comodoro Rivadavia"));
        cities.add(new City(4L, "Mendoza", "Piedras Blancas"));

        this.sessionFactory.getCurrentSession().save(cities.get(0));
        this.sessionFactory.getCurrentSession().save(cities.get(1));
        this.sessionFactory.getCurrentSession().save(cities.get(2));
        this.sessionFactory.getCurrentSession().save(cities.get(3));

        return this.sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT c.provincia FROM City c")
                .getResultList();
    }

    private List<String> getLocalidadesDeProvincia(){
        getProvincias();
        return this.sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT c.localidad FROM City c where c.provincia=?1")
                .setParameter(1, "Buenos Aires")
                .getResultList();
    }
}
