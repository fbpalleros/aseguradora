package org.aseguradora.repositories;

import org.aseguradora.config.HibernateConfig;
import org.aseguradora.entity.Insurance;
import org.aseguradora.entity.InsuranceType;
import org.aseguradora.repositories.impl.InsuranceRepositoryImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, InsuranceRepositoryImpl.class})
public class InsuranceRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private InsuranceRepository insuranceRepository;

    @BeforeEach
    public void init(){
        getInsurances();
        this.insuranceRepository = new InsuranceRepositoryImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObtengaUnSeguroPorSuId(){
        Insurance insuranceMock = getInsuranceById();
        Insurance insurance = this.insuranceRepository.findById(1L);

        assertThat(insurance, equalTo(insuranceMock));
        assertThat(insurance.getId(), equalTo(1L));
    }

    private void getInsurances(){
        Insurance i1 = new Insurance(1L,  InsuranceType.AUTOMOTOR);
        Insurance i2 = new Insurance(2L,  InsuranceType.PERSONA);
        Insurance i3 = new Insurance(3L,  InsuranceType.HOGAR);

        this.sessionFactory.getCurrentSession().save(i1);
        this.sessionFactory.getCurrentSession().save(i2);
        this.sessionFactory.getCurrentSession().save(i3);

    }

    private Insurance getInsuranceById(){
        return (Insurance) this.sessionFactory.getCurrentSession().createQuery("SELECT i FROM Insurance i WHERE i.id=?1")
                .setParameter(1, 1L)
                .getSingleResult();
    }


}
