package com.ricardococati.processor.application.job;

import com.ricardococati.processor.domain.model.TituloCdi;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class B3FileProcessingJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ItemReader<TituloCdi> reader;
    private final ItemProcessor<TituloCdi, TituloCdi> processor;
    private final ItemWriter<TituloCdi> writer;

    public B3FileProcessingJobConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                                     ItemReader<TituloCdi> reader, ItemProcessor<TituloCdi, TituloCdi> processor,
                                     ItemWriter<TituloCdi> writer) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.reader = reader;
        this.processor = processor;
        this.writer = writer;
    }

    @Bean
    public Job importJob() {
        return new JobBuilder("importB3FileJob", jobRepository)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("importDataStep", jobRepository)
                .<TituloCdi, TituloCdi>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}