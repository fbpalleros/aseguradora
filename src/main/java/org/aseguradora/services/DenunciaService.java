package org.aseguradora.services;

import java.util.List;

import org.aseguradora.entity.Denuncia;

public interface DenunciaService {

    List<Denuncia> findAll();
    
    List<Denuncia> findAllByCustomerId(Long customerId);

    Denuncia findById(Long id);

    void save(Denuncia denuncia);
}
