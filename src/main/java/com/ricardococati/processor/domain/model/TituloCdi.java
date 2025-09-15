package com.ricardococati.processor.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TituloCdi(
        String tipoRegistro,
        String tipoAtivo,
        String codigoTitulo,
        String nomeTitulo,
        BigDecimal valorNominal,
        LocalDate dataEmissao,
        LocalDate dataVencimento) {

    public TituloCdi withUpperCaseName() {
        return new TituloCdi(
                this.tipoRegistro,
                this.tipoAtivo,
                this.codigoTitulo,
                this.nomeTitulo.toUpperCase(),
                this.valorNominal,
                this.dataEmissao,
                this.dataVencimento
        );
    }
}