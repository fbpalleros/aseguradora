package org.aseguradora.repositories;

import org.aseguradora.entity.Complaint;

import java.util.List;

public interface ComplaintRepository {

    List<Complaint> findAll();

    Complaint findOne(Long id);

    List<Complaint> findByCustomerId(Long id);

    void save(Complaint complaint);

    void update(Complaint complaint);
}
