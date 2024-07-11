package com.augustczar.healthforall.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.augustczar.healthforall.domain.Beneficiario;

public interface BeneficiarioService {

	void delete(Beneficiario beneficiario);

	Optional<Beneficiario> findById(UUID beneficiarioId);

	List<Beneficiario> findAll();

	Optional<Beneficiario> findByNome(String nome);

	Beneficiario save(Beneficiario beneficiario);

}
