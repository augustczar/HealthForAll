package com.augustczar.healthforall.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.augustczar.healthforall.domain.Beneficiario;
import com.augustczar.healthforall.domain.Documento;
import com.augustczar.healthforall.enums.TipoDocumentos;
import com.augustczar.healthforall.respositories.DocumentoRepository;

@Service
public class DocumentoServiceImpl implements com.augustczar.healthforall.service.DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Override
	public List<Documento> saveAll(Collection<Documento> documentList) {
		return documentoRepository.saveAll(documentList);
	}

	@Override
	public Documento save(Documento documento) {
		return documentoRepository.save(documento);
	}

	@Override
	public List<Documento> findAll() {
		return documentoRepository.findAll();
	}

	@Override
	public List<Documento> findByBeneficiario(Beneficiario beneficiario) {
		
		return documentoRepository.findByBeneficiario(beneficiario);
	}

	@Override
	public void update(UUID documentoId, TipoDocumentos tipoDocumentos, String descricao, LocalDateTime dataAtualizacao) {
		documentoRepository.update(documentoId, tipoDocumentos, descricao, dataAtualizacao);
	}

	@Override
	public Optional<Documento> findByBeneficiarioId(UUID beneficiarioId) {
		return documentoRepository.findByBeneficiario(beneficiarioId);
	}

	@Override
	public Optional<Documento> findById(UUID documentoId) {
		return documentoRepository.findById(documentoId);
	}
}
