package com.augustczar.healthforall.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.augustczar.healthforall.domain.Documento;

@Service
public interface DocumentoService {

	public List<Documento> findAllByBeneficiarioId(UUID beneficiarioId);
}
