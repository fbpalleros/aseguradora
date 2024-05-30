package org.aseguradora.repositories;


import org.aseguradora.config.HibernateConfig;
import org.aseguradora.entity.Live;
import org.aseguradora.repositories.impl.LiveRepositoryImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, LiveRepositoryImpl.class})
public class LiveRepositoryTest {


    @Autowired
    private SessionFactory sessionFactory;

    private LiveRepository liveRepository;

    @BeforeEach
    public void init() {
        getLives();
        this.liveRepository = new LiveRepositoryImpl(this.sessionFactory);

    }

    @Test
    @Transactional
    @Rollback
    public void queSeObteganTodosLosOficios() {
        List<String> livesMock = getOficios();

        List<String> lives = this.liveRepository.listarTodasLosOficios();

        assertThat(lives, equalTo(livesMock));
        assertThat(lives.size(), equalTo(3));
        assertThat(lives.size(), equalTo(livesMock.size()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObteganTodosLosAnioPorOficio() {
        List<Integer> livesMock = getAnios();

        List<Integer> lives = this.liveRepository.buscarAnioPorOficio("Chofer");

        assertThat(lives, equalTo(livesMock));
        assertThat(lives.size(), equalTo(2));
        assertThat(lives.size(), equalTo(livesMock.size()));
    }

//    @Test
//    @Transactional
//    @Rollback
//    public void queSeObtengaElPrecioPorAnioYOficio() {
//        Double livesMock = getPrecio();
//
//        Double lives = this.liveRepository.buscarPrecioPorAnioYOficio("Chofer" , 2009);
//
//       // assertThat(lives, equalTo(livesMock));
//        assertThat(lives, equalTo(400000));
//    }


    private void getLives() {

        Live live1 = new Live(1L, "Chofer",  2005, 100000.00);
        Live live2 = new Live(2L, "Profesor",  2008, 300000.00);
        Live live3 = new Live(3L, "Chofer",  2009, 400000.00);
        Live live4 = new Live(4L, "Domestico",  2005, 500000.00);

        this.sessionFactory.getCurrentSession().save(live1);
        this.sessionFactory.getCurrentSession().save(live2);
        this.sessionFactory.getCurrentSession().save(live3);
        this.sessionFactory.getCurrentSession().save(live4);
    }



    private List<String> getOficios() {
        return this.sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT c.oficio FROM Live c").getResultList();
    }

    private List<Integer> getAnios() {
        return this.sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT c.anio FROM Live c where c.oficio=?1")
                .setParameter(1, "Chofer").getResultList();
    }

    private Double getPrecio() {
        return (Double) this.sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT  c.precio FROM Live c WHERE c.oficio=?1 and c.anio=?2")
                .setParameter(1, "Chofer")
                .setParameter(2, "2009")
                .getSingleResult();
    }


}
