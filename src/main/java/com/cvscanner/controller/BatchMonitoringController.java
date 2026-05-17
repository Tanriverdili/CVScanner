package com.cvscanner.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
public class BatchMonitoringController {
    private final JobExplorer jobExplorer;

    @GetMapping("/status/{jobName}")
    public List<String> getJobStatus(@PathVariable String jobName) {
        return jobExplorer.getJobInstances(jobName, 0, 10)
                .stream()
                .flatMap(instance -> jobExplorer.getJobExecutions(instance).stream())
                .map(exec -> jobName +  " " + exec.getStatus().toString())
                .toList();
    }
    @GetMapping("/steps/{jobName}")
    public List<String> getStepDetails(@PathVariable String jobName) {
        return jobExplorer.getJobInstances(jobName, 0, 10)
                .stream()
                .flatMap(instance -> jobExplorer.getJobExecutions(instance).stream())
                .flatMap(exec -> exec.getStepExecutions().stream())
                .map(step ->
                        "Step: " + step.getStepName()
                                + " Read: " + step.getReadCount()
                                + "  Write: " + step.getWriteCount()
                                + "  Skip: " + step.getSkipCount()
                                + "  Status: " + step.getStatus()
                )
                .toList();
    }
}




























