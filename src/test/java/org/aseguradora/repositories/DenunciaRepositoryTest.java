package org.aseguradora.repositories;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.transaction.Transactional;

import org.aseguradora.config.HibernateConfig;
import org.aseguradora.entity.Denuncia;
import org.aseguradora.entity.Policy;
import org.aseguradora.repositories.impl.DenunciaRepositoryImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DenunciaRepositoryImpl.class})
public class DenunciaRepositoryTest {

    
    @Autowired
    private DenunciaRepository denunciaRepository;
    
    private final Long ID_TEST = 1L;


    @Test
    @Transactional
    public void queSePuedaGuardarUnaPoliza() {
    	Policy policy = new Policy();
    	policy.setId(2L);
    	
        Denuncia denuncia = new Denuncia();
        denuncia.setAccidentDate("2024-01-01");
        denuncia.setAccidentHour("19:00");
        denuncia.setDescription("test");
        denuncia.setId(ID_TEST);

        this.denunciaRepository.save(denuncia);

        assertThat(denuncia, equalTo(denuncia));
    }
    
	/*
	 * @Test
	 * 
	 * @Transactional public void queSeObteganUltimaDenuncia() {
	 * 
	 * 
	 * Denuncia denuncia = this.denunciaRepository.findById(ID_TEST);
	 * 
	 * assertThat(denuncia.getDescription(), equals("test"));
	 * 
	 * }
	 */


}
