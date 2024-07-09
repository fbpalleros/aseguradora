package org.aseguradora.services;

import org.aseguradora.entity.Complaint;

import java.util.List;

public interface ComplaintService {

    void save(Complaint complaint);

    List<Complaint> findByCustomerId(Long id);

    Complaint findOne(Long idComplaint);

    List<Complaint> findAll();

    void update(Complaint complaint);
}
