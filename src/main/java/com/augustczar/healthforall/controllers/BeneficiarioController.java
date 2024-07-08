package com.augustczar.healthforall.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.augustczar.healthforall.dtos.BeneficiarioDto;
import com.augustczar.healthforall.service.BeneficiarioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

	@Autowired
    private final BeneficiarioService beneficiarioService = null;

    @PostMapping
    public Beneficiario create(@RequestBody BeneficiarioDto beneficiarioDto) {
    	
    	var beneficiario = new Beneficiario();
		BeanUtils.copyProperties(beneficiarioDto, beneficiario);
        beneficiario.setDataInclusao(LocalDateTime.now());
        beneficiario.setDataAtualizacao(LocalDateTime.now());		
        return beneficiarioService.save(beneficiario);
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
    public ResponseEntity<Void> delete(@PathVariable UUID beneficiarioId) {
        beneficiarioService.deleteById(beneficiarioId);
        return ResponseEntity.noContent().build();
    }
}
