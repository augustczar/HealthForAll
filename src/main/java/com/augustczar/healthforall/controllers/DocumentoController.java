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

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/documentos")
public class DocumentoController {

	private DocumentoService documentoService;
	
	private BeneficiarioService BeneficiarioService;

    @GetMapping("/beneficiario/{beneficiarioId}")
    public ResponseEntity<Object> getByBeneficiarioId(@PathVariable(value = "beneficiarioId") UUID beneficiarioId) {
       		Optional<Beneficiario> beneficiarioModelOptional = BeneficiarioService.findById(beneficiarioId);
     		if (!beneficiarioModelOptional.isPresent()) {
     			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beneficiary Not Found!");
    		}
     		
     		List<Documento> documentos = documentoService.findByBeneficiarioId(beneficiarioId);
     		
    		return ResponseEntity.status(HttpStatus.OK).body(documentos);	
    }
}
