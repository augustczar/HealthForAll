package com.augustczar.healthforall.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentoDto {

	private UUID documentoId;
	private String tipoDocumento;
	private String descricao;
	private LocalDateTime dataInclusao;
	private LocalDateTime dataAtualizacao;
	
	private BeneficiarioDto beneficiariosDto;
}
