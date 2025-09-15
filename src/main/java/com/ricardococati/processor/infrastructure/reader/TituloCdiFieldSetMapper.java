package com.ricardococati.processor.infrastructure.reader;

import com.ricardococati.processor.domain.model.TituloCdi;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class TituloCdiFieldSetMapper implements FieldSetMapper<TituloCdi> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public TituloCdi mapFieldSet(FieldSet fieldSet) {
        return new TituloCdi(
                fieldSet.readString("tipoRegistro"),
                fieldSet.readString("tipoAtivo"),
                fieldSet.readString("codigoTitulo"),
                fieldSet.readString("nomeTitulo"),
                new BigDecimal(fieldSet.readString("valorNominal")),
                LocalDate.parse(fieldSet.readString("dataEmissao"), DATE_FORMATTER),
                LocalDate.parse(fieldSet.readString("dataVencimento"), DATE_FORMATTER)
        );
    }
}
