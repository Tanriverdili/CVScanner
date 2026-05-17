package com.cvscanner.repository;
import com.cvscanner.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {
    List<CandidateEntity>
    findBySkillsContainingAndExperienceGreaterThanEqual(
            String skill,
            int experience
    );
}