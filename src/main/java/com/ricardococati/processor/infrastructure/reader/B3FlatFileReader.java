package com.ricardococati.processor.infrastructure.reader;

import com.ricardococati.processor.domain.model.TituloCdi;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class B3FlatFileReader {

    private static final String FILE_PATH = "/home/ricardococati/Programas/arquivos";
    private final TituloCdiFieldSetMapper titulosCdiFieldSetMapper;

    public B3FlatFileReader(TituloCdiFieldSetMapper titulosCdiFieldSetMapper) {
        this.titulosCdiFieldSetMapper = titulosCdiFieldSetMapper;
    }

    @Bean
    public FlatFileItemReader<TituloCdi> reader() {
        return new FlatFileItemReaderBuilder<TituloCdi>()
                .name("b3ItemReader")
                .resource(new FileSystemResource(FILE_PATH))
                .fixedLength()
                .columns(
                        new Range(1, 10),
                        new Range(11, 20),
                        new Range(21, 30),
                        new Range(31, 50),
                        new Range(51, 64),
                        new Range(65, 72),
                        new Range(73, 80)
                )
                .names("tipoRegistro", "tipoAtivo", "codigoTitulo", "nomeTitulo", "valorNominal", "dataEmissao", "dataVencimento")
                .fieldSetMapper(titulosCdiFieldSetMapper)
                .strict(false)
                .build();
    }
}
