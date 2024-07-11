package com.augustczar.healthforall.respositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.augustczar.healthforall.domain.Beneficiario;
import com.augustczar.healthforall.domain.Documento;
import com.augustczar.healthforall.enums.TipoDocumentos;

import jakarta.transaction.Transactional;

@Component
public interface DocumentoRepository extends JpaRepository<Documento, UUID> {

	List<Documento> findByBeneficiario(Beneficiario beneficiario);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE tb_documento set tipo_documento = :tipoDocumento, descricao = :descricao, data_atualizacao = :dataAtualizacao WHERE documento_id = :documentoId", nativeQuery = true)
	void update(@Param("documentoId") UUID documentoId, @Param("tipoDocumento") TipoDocumentos tipoDocumentos, @Param("descricao") String descricao, @Param("dataAtualizacao") LocalDateTime dataAtualizacao);

	@Transactional
	@Query(value = "SELECT FROM tb_documento WHERE beneficiario_beneficiario_id = :beneficiarioId", nativeQuery = true)
	Optional<Documento> findByBeneficiario(@Param("beneficiarioId") UUID beneficiarioId);

}
