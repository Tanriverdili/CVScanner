package com.cvscanner.batch.config;
import com.cvscanner.batch.listener.CVSkipListener;
import com.cvscanner.batch.processor.CVProcessor;
import com.cvscanner.batch.reader.CVReader;
import com.cvscanner.batch.writer.CVWriter;
import com.cvscanner.entity.CandidateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import java.nio.file.Path;

@Configuration
@RequiredArgsConstructor
public class StepConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final CVReader cvReader;
    private final CVProcessor cvProcessor;
    private final CVWriter cvWriter;
    private final CVSkipListener cvSkipListener;

    @Bean
    public Step cvStep() {
        return new StepBuilder("cvStep", jobRepository)
                .<Path, CandidateEntity>chunk(5, transactionManager)
                .reader(cvReader)
                .processor(cvProcessor)
                .writer(cvWriter)
                .faultTolerant()
                .retry(Exception.class)
                .retryLimit(3)
                .skip(Exception.class)
                .skipLimit(100)
                .listener(cvSkipListener)
                .build();
    }
}






