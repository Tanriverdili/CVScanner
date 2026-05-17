package com.cvscanner.batch.processor;
import com.cvscanner.entity.CandidateEntity;
import com.cvscanner.extraction.ExperienceExtractionService;
import com.cvscanner.extraction.NameExtractionService;
import com.cvscanner.extraction.SkillExtractionService;
import com.cvscanner.service.TextReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import java.nio.file.Path;

@Component
@Slf4j
public class CVProcessor implements ItemProcessor<Path, CandidateEntity> {
    private final TextReaderService textReaderService;
    private final NameExtractionService nameExtractionService;
    private final SkillExtractionService skillExtractionService;
    private final ExperienceExtractionService experienceExtractionService;

    public CVProcessor(TextReaderService textReaderService,
                       NameExtractionService nameExtractionService,
                       SkillExtractionService skillExtractionService,
                       ExperienceExtractionService experienceExtractionService) {
        this.textReaderService = textReaderService;
        this.nameExtractionService = nameExtractionService;
        this.skillExtractionService = skillExtractionService;
        this.experienceExtractionService = experienceExtractionService;
    }
    @Override
    public CandidateEntity process(Path file) {
        try {
            String content = textReaderService.readTextFile(file);

            if (content.contains("@@@") || content.contains("###")) {
                throw new RuntimeException("Broken CV file");
            }
            CandidateEntity candidate = new CandidateEntity();
            candidate.setName(nameExtractionService.extractName(content));
            candidate.setSkills(skillExtractionService.extractSkills(content).toString());
            candidate.setExperience(experienceExtractionService.extractExperience(content));
            log.info("Processed CV: {}", file.getFileName());
            return candidate;

        } catch (Exception e) {
            log.error("Failed to process CV: {}", file.getFileName());
            throw new RuntimeException("CV processing failed: " + file.getFileName(), e);
        }
    }
}






























