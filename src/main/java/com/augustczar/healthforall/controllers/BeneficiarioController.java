package com.augustczar.healthforall.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augustczar.healthforall.domain.Beneficiario;
import com.augustczar.healthforall.domain.Documento;
import com.augustczar.healthforall.dtos.BeneficiarioDto;
import com.augustczar.healthforall.dtos.DocumentoDto;
import com.augustczar.healthforall.service.BeneficiarioService;
import com.augustczar.healthforall.service.DocumentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/beneficiarios")
@Tag(name = "Saúde para todos")
public class BeneficiarioController {

	@Autowired
    private final BeneficiarioService beneficiarioService = null;


	@Autowired
	DocumentoService documentService;
	
	@Operation(summary = "Cadastrar um benefíciario junto com seus documentos", method = "POST")
	@ApiResponses(value = {
			@ApiResponse( responseCode = "200", description = "Benefíciario registrado com sucesso! "),
			@ApiResponse( responseCode = "422", description = "Dados da requisição inválidos!"),
			@ApiResponse( responseCode = "400", description = "Parametros inválidos"),
			@ApiResponse( responseCode = "500", description = "Error ao registrar Benefíciario!"),
	})
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody BeneficiarioDto beneficiarioDto) {
    	
		var beneficiaryName = beneficiarioService.findByNome(beneficiarioDto.getNome());

		if (beneficiaryName.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Error: beneficiaryName, this beneficiary is already registered!");
		}

		var beneficiario = new Beneficiario();
		beneficiario.setNome(beneficiarioDto.getNome());
		beneficiario.setDataNascimento(beneficiarioDto.getDataNascimento());
		beneficiario.setTelefone(beneficiarioDto.getTelefone());
		beneficiario.setDataInclusao(LocalDateTime.now(ZoneId.of("UTC")));
		beneficiario.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));
		Optional<Beneficiario> beneficiarySave = Optional.of(beneficiarioService.save(beneficiario));

		for (DocumentoDto benefDoc : beneficiarioDto.getDocumentosDtos()) {
			Documento documentModels = new Documento();
			documentModels.setTipoDocumentos(benefDoc.getTipoDocumentos());
			documentModels.setDescricao(benefDoc.getDescricao());

			documentModels.setBeneficiario(beneficiarySave.get());
			documentModels.setDataInclusao(LocalDateTime.now(ZoneId.of("UTC")));
			documentModels.setDataAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));
			documentService.save(documentModels);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Registration completed successfully!");
    }

    @GetMapping
    public List<Beneficiario> getAll() {
        return beneficiarioService.findAll();
    }

    @GetMapping("/{beneficiarioId}")
    public ResponseEntity<Beneficiario> getById(@PathVariable UUID beneficiarioId) {
        return beneficiarioService.findById(beneficiarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{beneficiarioId}")
    public ResponseEntity<Beneficiario> update(@PathVariable UUID beneficiarioId, @RequestBody BeneficiarioDto beneficiarioDto) {
        
    	var beneficiario = new Beneficiario();
		BeanUtils.copyProperties(beneficiarioDto, beneficiario);
    	
    	return beneficiarioService.findById(beneficiarioId)
                .map(existingBeneficiario -> {
                    beneficiario.setBeneficiarioId(existingBeneficiario.getBeneficiarioId());
                    beneficiario.setDataAtualizacao(LocalDateTime.now());
                    return ResponseEntity.ok(beneficiarioService.save(beneficiario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{beneficiarioId}")
   	public ResponseEntity<Object> delete(@PathVariable(value = "beneficiarioId") UUID beneficiarioId) {
    	Optional<Beneficiario> beneficiaryModelOptional = beneficiarioService.findById(beneficiarioId);
    	if (!beneficiaryModelOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beneficiary not found!");
    	}
    	beneficiarioService.delete(beneficiaryModelOptional.get());
    	return ResponseEntity.status(HttpStatus.OK).body("Beneficiary deleted successfully!");
    }
}
