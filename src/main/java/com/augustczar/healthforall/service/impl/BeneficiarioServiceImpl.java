package com.augustczar.healthforall.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.augustczar.healthforall.domain.Beneficiario;
import com.augustczar.healthforall.respositories.BeneficiarioRepository;
import com.augustczar.healthforall.service.BeneficiarioService;

@Component
public class BeneficiarioServiceImpl implements BeneficiarioService {

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;
	
	@Override
	public Beneficiario save(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
	}

	@Override
	public List<Beneficiario> findAll() {
		return beneficiarioRepository.findAll();
	}

	@Override
	public Optional<Beneficiario> findById(UUID beneficiarioId) {
		return beneficiarioRepository.findById(beneficiarioId);
	}

	@Override
	public void deleteById(UUID beneficiarioId) {
		beneficiarioRepository.deleteById(beneficiarioId);
	}

}
