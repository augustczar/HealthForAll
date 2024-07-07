package com.augustczar.healthforall.respositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.augustczar.healthforall.domain.Beneficiario;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, UUID> {

}
