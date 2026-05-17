package com.cvscanner.service;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CVProcessingService {
    private final JobLauncher jobLauncher;
    private final Job cvJob;
    private final EmailService emailService;
    private  final Logger logger = LoggerFactory.getLogger(CVProcessingService.class);

    public void runJob() {
        logger.info("CV Batch job started");

        try {
            jobLauncher.run(
                    cvJob,
                    new JobParametersBuilder()
                            .addLong("time", System.currentTimeMillis())
                            .toJobParameters()
            );
            logger.info("CV Batch Job Completed");
            logger.info("CV Batch job finished successfully");


        } catch (Exception e) {
            logger.error("Error while processing CV batch job", e);
            throw new RuntimeException(e);
        }
    }
}











































































