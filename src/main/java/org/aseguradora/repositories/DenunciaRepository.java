package org.aseguradora.repositories;

import java.util.List;

import org.aseguradora.entity.Denuncia;

public interface DenunciaRepository {

    List<Denuncia> findAll();
    
    Denuncia findById(Long id);
    
    List<Denuncia> findAllByCustomerId(Long customerId);

    void save(Denuncia denuncia);
}
