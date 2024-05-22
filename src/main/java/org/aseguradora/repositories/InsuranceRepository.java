package org.aseguradora.repositories;

import org.aseguradora.entity.Insurance;
import java.util.List;

public interface InsuranceRepository {

    List<Insurance> findAll();

    Insurance findById(Long id);
}
