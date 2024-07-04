package org.aseguradora.repositories;

import org.aseguradora.entity.Insurance;
import java.util.List;

public interface InsuranceRepository {

    Insurance findById(Long id);
}
