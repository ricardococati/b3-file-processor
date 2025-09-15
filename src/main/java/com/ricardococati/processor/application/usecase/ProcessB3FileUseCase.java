package com.ricardococati.processor.application.usecase;

import com.ricardococati.processor.application.port.FileProcessingPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.ricardococati.processor.shared.constants.Constants.PROCESSAMENTO_DO_ARQUIVO;

@Component
public class ProcessB3FileUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessB3FileUseCase.class);
    private final FileProcessingPort fileProcessingPort;

    public ProcessB3FileUseCase(FileProcessingPort fileProcessingPort) {
        this.fileProcessingPort = fileProcessingPort;
    }

    public void execute(final String filePath) {
        LOGGER.info(PROCESSAMENTO_DO_ARQUIVO, filePath);
        fileProcessingPort.processFile(filePath);
    }
}