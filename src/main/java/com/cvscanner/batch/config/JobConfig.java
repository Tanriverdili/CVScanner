package com.cvscanner.batch.config;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfig {
    private final JobRepository jobRepository;
    private final Step cvStep;

    @Bean
    public Job cvJob() {
        return new JobBuilder("cvJob", jobRepository)
                .start(cvStep)
                .build();
    }
}