package com.augustczar.healthforall.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.augustczar.healthforall.domain.Documento;
import com.augustczar.healthforall.respositories.DocumentoRepository;

@Component
public class DocumentoServiceImpl implements com.augustczar.healthforall.service.DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Override
	public List<Documento> findAllByBeneficiarioId(UUID beneficiarioId) {
		return documentoRepository.findByBeneficiarioId(beneficiarioId);
	}

}
