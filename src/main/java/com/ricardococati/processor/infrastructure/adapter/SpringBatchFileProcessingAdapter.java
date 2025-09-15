package com.ricardococati.processor.infrastructure.adapter;

import com.ricardococati.processor.application.port.FileProcessingPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.ricardococati.processor.shared.constants.Constants.ERRO_AO_INICIAR_O_JOB;
import static com.ricardococati.processor.shared.constants.Constants.PROCESSAMENTO_DO_ARQUIVO;
import static com.ricardococati.processor.shared.constants.Constants.SUCESSO_PARA_O_ARQUIVO;

@Component
public class SpringBatchFileProcessingAdapter implements FileProcessingPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchFileProcessingAdapter.class);
    private final JobLauncher jobLauncher;
    private final Job importJob;

    public SpringBatchFileProcessingAdapter(JobLauncher jobLauncher, Job importJob) {
        this.jobLauncher = jobLauncher;
        this.importJob = importJob;
    }

    @Override
    public void processFile(String filePath) {
        LOGGER.info(PROCESSAMENTO_DO_ARQUIVO, filePath);
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", filePath)
                .addDate("runDate", new Date())
                .toJobParameters();

        try {
            jobLauncher.run(importJob, jobParameters);
            LOGGER.info(SUCESSO_PARA_O_ARQUIVO, filePath);
        } catch (JobExecutionAlreadyRunningException |
                 JobRestartException |
                 JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            LOGGER.error(ERRO_AO_INICIAR_O_JOB, e.getMessage());
            throw new RuntimeException(ERRO_AO_INICIAR_O_JOB, e);
        }
    }
}