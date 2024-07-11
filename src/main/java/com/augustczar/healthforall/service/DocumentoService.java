package com.augustczar.healthforall.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.augustczar.healthforall.domain.Beneficiario;
import com.augustczar.healthforall.domain.Documento;
import com.augustczar.healthforall.enums.TipoDocumentos;

@Service
public interface DocumentoService {

	Documento save(Documento documentModelsLit);

	List<Documento> findAll();

	List<Documento> findByBeneficiario(Beneficiario beneficiario);

	void update(UUID documentoId, TipoDocumentos tipoDocumentos, String descricao, LocalDateTime dataAtualizacao);

	Optional<Documento> findByBeneficiarioId(UUID beneficiarioId);

	Optional<Documento> findById(UUID documentoId);

	List<Documento> saveAll(Collection<Documento> documentList);
}
