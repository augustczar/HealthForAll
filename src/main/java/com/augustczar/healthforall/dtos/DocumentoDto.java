package com.augustczar.healthforall.dtos;

import com.augustczar.healthforall.enums.TipoDocumentos;
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

	private TipoDocumentos tipoDocumentos;
	
	private String descricao;

}
