package com.cvscanner.batch.listener;
import com.cvscanner.entity.CandidateEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CVSkipListener implements SkipListener<String, CandidateEntity> {

    @Override
    public void onSkipInRead(Throwable t) {
        log.error("SKIP in READ: {}", t.getMessage());
    }

    @Override
    public void onSkipInProcess(String item, Throwable t) {
        log.error("SKIP CV ITEM: {} | ERROR: {}", item, t.getMessage());
    }

    @Override
    public void onSkipInWrite(CandidateEntity item, Throwable t) {
        log.error("SKIP WRITE: {} | ERROR: {}", item, t.getMessage());
    }
}