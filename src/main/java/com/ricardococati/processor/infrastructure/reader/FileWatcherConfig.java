package com.ricardococati.processor.infrastructure.reader;

import com.ricardococati.processor.application.usecase.ProcessB3FileUseCase;
import com.ricardococati.processor.shared.properties.AppProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
public class FileWatcherConfig {

    private final ProcessB3FileUseCase processB3FileUseCase;
    private final AppProperties properties;

    public FileWatcherConfig(
            final ProcessB3FileUseCase processB3FileUseCase,
            final AppProperties properties) {
        this.processB3FileUseCase = processB3FileUseCase;
        this.properties = properties;
    }

    @Bean
    public MessageChannel fileProcessingChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File(properties.getPath()));
        source.setFilter(new AcceptOnceFileListFilter<>());
        return source;
    }

    @Bean
    public MessageHandler fileProcessingMessageHandler() {
        return message -> {
            File file = (File) message.getPayload();
            System.out.println("Novo arquivo detectado: " + file.getAbsolutePath());
            processB3FileUseCase.execute(file.getAbsolutePath());
        };
    }
}
