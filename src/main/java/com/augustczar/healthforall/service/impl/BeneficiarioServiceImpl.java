package com.augustczar.healthforall.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.augustczar.healthforall.domain.Beneficiario;
import com.augustczar.healthforall.domain.Documento;
import com.augustczar.healthforall.respositories.BeneficiarioRepository;
import com.augustczar.healthforall.respositories.DocumentoRepository;
import com.augustczar.healthforall.service.BeneficiarioService;

import jakarta.transaction.Transactional;

@Service
public class BeneficiarioServiceImpl implements BeneficiarioService {

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Transactional
	@Override
	public void delete(Beneficiario beneficiario) {
		List<Documento> documentos = documentoRepository.findByBeneficiario(beneficiario);
		if (!documentos.isEmpty()) {
			documentoRepository.deleteAll(documentos);
		}
		beneficiarioRepository.delete(beneficiario);
	}
	
	@Override
	public Optional<Beneficiario> findById(UUID beneficiarioId) {
		return beneficiarioRepository.findById(beneficiarioId);
	}
	
	@Override
	public List<Beneficiario> findAll() {
		return beneficiarioRepository.findAll();
	}

	@Override
	public Optional<Beneficiario> findByNome(String nome){
		return beneficiarioRepository.findByNome(nome);
	}
	
	@Override
	public Beneficiario save(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
	}
	
}
