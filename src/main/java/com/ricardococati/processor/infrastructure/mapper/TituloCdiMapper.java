package com.ricardococati.processor.infrastructure.mapper;

import com.ricardococati.processor.domain.model.TituloCdi;
import com.ricardococati.processor.infrastructure.model.TituloCdiEntity;
import org.springframework.stereotype.Component;

@Component
public class TituloCdiMapper {

    public TituloCdiEntity toEntity(TituloCdi domainRecord) {
        return new TituloCdiEntity(
                domainRecord.tipoRegistro(),
                domainRecord.tipoAtivo(),
                domainRecord.codigoTitulo(),
                domainRecord.nomeTitulo(),
                domainRecord.valorNominal(),
                domainRecord.dataEmissao(),
                domainRecord.dataVencimento()
        );
    }
}