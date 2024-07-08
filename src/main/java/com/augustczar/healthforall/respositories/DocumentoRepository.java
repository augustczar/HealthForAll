package com.augustczar.healthforall.respositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.augustczar.healthforall.domain.Beneficiario;
import com.augustczar.healthforall.domain.Documento;

@Component
public interface DocumentoRepository extends JpaRepository<Documento, UUID> {

	List<Documento> findByBeneficiarioBeneficiarioId(UUID beneficiarioId);

}
