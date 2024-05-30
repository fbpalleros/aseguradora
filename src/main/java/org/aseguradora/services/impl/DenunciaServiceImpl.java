package org.aseguradora.services.impl;

import java.util.List;

import org.aseguradora.entity.Denuncia;
import org.aseguradora.repositories.DenunciaRepository;
import org.aseguradora.services.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DenunciaServiceImpl implements DenunciaService {

	private DenunciaRepository denunciaRepository;

    @Autowired
    public DenunciaServiceImpl(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }
	
	
	@Override
	public List<Denuncia> findAll() {
		return denunciaRepository.findAll();
	}

	@Override
	public Denuncia findById(Long id) {
		return denunciaRepository.findById(id);
	}

	@Override
	public void save(Denuncia denuncia) {
		denunciaRepository.save(denuncia);
	}


	@Override
	public List<Denuncia> findAllByCustomerId(Long customerId) {
		return denunciaRepository.findAllByCustomerId(customerId);
	}
}
