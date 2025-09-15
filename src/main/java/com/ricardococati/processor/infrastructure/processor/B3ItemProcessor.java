package com.ricardococati.processor.infrastructure.processor;

import com.ricardococati.processor.domain.model.TituloCdi;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class B3ItemProcessor implements ItemProcessor<TituloCdi, TituloCdi> {

    @Override
    public TituloCdi process(TituloCdi item) throws Exception {
        return item.withUpperCaseName();
    }

}