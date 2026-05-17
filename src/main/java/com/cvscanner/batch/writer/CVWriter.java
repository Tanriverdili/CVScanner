package com.cvscanner.batch.writer;
import com.cvscanner.entity.CandidateEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class CVWriter implements ItemWriter<CandidateEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends CandidateEntity> chunk) {
        for (CandidateEntity item : chunk.getItems()) {
            if (item == null) continue;
            entityManager.persist(item);
        }
    }
}








