package com.augustczar.healthforall.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import com.augustczar.healthforall.enums.TipoDocumentos;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@DynamicUpdate
@Table(name = "TB_DOCUMENTO")
public class Documento implements Serializable{
	
	private static final long serialVersionUID = -9088125514855165479L;
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID documentoId;
	
	private Integer tipoDocumentos;
	
	@Column(nullable = false, length = 250)
	private String descricao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDateTime dataInclusao;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDateTime dataAtualizacao;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	private Beneficiario beneficiario;
	
	public TipoDocumentos getTipoDocumentos() {
		return TipoDocumentos.valueOf(tipoDocumentos);
	}
	
	public void setTipoDocumentos(TipoDocumentos tipoDocumento) {
		if(tipoDocumentos != null) {
			this.tipoDocumentos = tipoDocumento.getCode();
		}
	}
}
