package com.augustczar.healthforall.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.augustczar.healthforall.domain.Beneficiario;

@Service
public interface BeneficiarioService {

    public Beneficiario save(Beneficiario beneficiario);

    public List<Beneficiario> findAll();
    
    public Optional<Beneficiario> findById(UUID beneficiarioId);

    public void deleteById(UUID beneficiarioId);

}
