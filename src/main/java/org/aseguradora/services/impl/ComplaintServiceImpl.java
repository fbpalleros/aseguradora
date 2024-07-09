package org.aseguradora.services.impl;

import org.aseguradora.entity.Complaint;
import org.aseguradora.repositories.ComplaintRepository;
import org.aseguradora.repositories.impl.ComplaintRepositoryImpl;
import org.aseguradora.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository){
        this.complaintRepository = complaintRepository;
    }

    @Override
    @Transactional
    public void save(Complaint complaint) {
        this.complaintRepository.save(complaint);
    }

    @Override
    @Transactional
    public List<Complaint> findByCustomerId(Long id) {
        return this.complaintRepository.findByCustomerId(id);
    }

    @Override
    @Transactional
    public Complaint findOne(Long idComplaint) {
        return complaintRepository.findOne(idComplaint);
    }

    @Override
    @Transactional
    public List<Complaint> findAll() {
        return complaintRepository.findAll();
    }

    @Override
    @Transactional
    public void update(Complaint complaint) {
        this.complaintRepository.update(complaint);
    }
}
