package com.cvscanner.service;
import com.cvscanner.entity.CandidateEntity;
import com.cvscanner.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public List<CandidateEntity> filterCandidates(
            String skill,
            int experience
    ) {
        return candidateRepository
                .findBySkillsContainingAndExperienceGreaterThanEqual(
                        skill,
                        experience
                );
    }
}