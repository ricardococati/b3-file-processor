package com.ricardococati.processor.infrastructure.writer;

import com.ricardococati.processor.domain.model.TituloCdi;
import com.ricardococati.processor.infrastructure.mapper.TituloCdiMapper;
import com.ricardococati.processor.infrastructure.repository.TituloCdiRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class TituloCdiItemWriter implements ItemWriter<TituloCdi> {

    private final TituloCdiRepository repository;
    private final TituloCdiMapper mapper;

    public TituloCdiItemWriter(TituloCdiRepository repository, TituloCdiMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void write(Chunk<? extends TituloCdi> chunk) {
        var entities = chunk.getItems().stream()
                .map(mapper::toEntity)
                .toList();
        repository.saveAll(entities);
    }
}