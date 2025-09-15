package com.ricardococati.processor.infrastructure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "titulos_cdi")
public class TituloCdiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoRegistro;
    private String tipoAtivo;
    private String codigoTitulo;
    private String nomeTitulo;
    private BigDecimal valorNominal;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;


    public TituloCdiEntity() {
        // Construtor padrão exigido pelo JPA
    }

    public TituloCdiEntity(String tipoRegistro, String tipoAtivo, String codigoTitulo, String nomeTitulo,
                           BigDecimal valorNominal, LocalDate dataEmissao, LocalDate dataVencimento) {
        this.tipoRegistro = tipoRegistro;
        this.tipoAtivo = tipoAtivo;
        this.codigoTitulo = codigoTitulo;
        this.nomeTitulo = nomeTitulo;
        this.valorNominal = valorNominal;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipoRegistro() { return tipoRegistro; }
    public void setTipoRegistro(String tipoRegistro) { this.tipoRegistro = tipoRegistro; }
    public String getTipoAtivo() { return tipoAtivo; }
    public void setTipoAtivo(String tipoAtivo) { this.tipoAtivo = tipoAtivo; }
    public String getCodigoTitulo() { return codigoTitulo; }
    public void setCodigoTitulo(String codigoTitulo) { this.codigoTitulo = codigoTitulo; }
    public String getNomeTitulo() { return nomeTitulo; }
    public void setNomeTitulo(String nomeTitulo) { this.nomeTitulo = nomeTitulo; }
    public BigDecimal getValorNominal() { return valorNominal; }
    public void setValorNominal(BigDecimal valorNominal) { this.valorNominal = valorNominal; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }
}
