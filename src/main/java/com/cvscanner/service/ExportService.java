package com.cvscanner.service;
import com.cvscanner.entity.CandidateEntity;
import com.cvscanner.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExportService {
    private final CandidateRepository candidateRepository;

    public Path exportToCsv() {

        try {
            List<CandidateEntity> candidates =
                    candidateRepository.findAll();

            Path path = Path.of("candidates.csv");

            PrintWriter writer =
                    new PrintWriter(Files.newBufferedWriter(path));

            writer.println("Name,Skills,Experience");

            for (CandidateEntity c : candidates) {
                writer.println(
                        c.getName() + "," +
                                c.getSkills() + "," +
                                c.getExperience()
                );
            }
            writer.close();
            return path;

        } catch (Exception e) {
            throw new RuntimeException("CSV export failed");
        }
    }
}