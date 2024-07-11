package com.augustczar.healthforall.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augustczar.healthforall.domain.Beneficiario;
import com.augustczar.healthforall.domain.Documento;
import com.augustczar.healthforall.service.BeneficiarioService;
import com.augustczar.healthforall.service.DocumentoService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/documentos")
public class DocumentoController {

	private DocumentoService documentoService;
	
	private BeneficiarioService beneficiarioService;

	@Operation(summary = "Listar todos os documentos de um beneficiário a partir de seu id", method = "GET")
	@ApiResponses(value = {
			@ApiResponse( responseCode = "200", description = "Busca realizada com sucesso! "),
			@ApiResponse( responseCode = "422", description = "Dados da requisição inválidos!"),
			@ApiResponse( responseCode = "400", description = "Parametros inválidos"),
			@ApiResponse( responseCode = "500", description = "Error ao realizar busca Benefíciario!"),
	})	
    @GetMapping("/beneficiario/{beneficiarioId}")
	public ResponseEntity<Object> getAllDocumentByBeneficiario(@PathVariable(value = "beneficiarioId") UUID beneficiarioId){
		Optional<Beneficiario> beneficiaryModelOptional = beneficiarioService.findById(beneficiarioId);
 		if (!beneficiaryModelOptional.isPresent()) {
 			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beneficiary Not Found!");
		}
 		
 		List<Documento> documentos = documentoService.findByBeneficiario(beneficiaryModelOptional.get());
 		
		return ResponseEntity.status(HttpStatus.OK).body(documentos);	
	}
	
	@Hidden
	@GetMapping
	public ResponseEntity<List<Documento>> getAllDocument() {
		return ResponseEntity.status(HttpStatus.OK).body(documentoService.findAll());
	}
}
