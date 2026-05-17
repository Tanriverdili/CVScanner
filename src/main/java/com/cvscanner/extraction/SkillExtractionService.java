package com.cvscanner.extraction;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkillExtractionService {
    public List<String> extractSkills(String content) {
        List<String> skills = new ArrayList<>();
        String lower = content.toLowerCase();

        if (lower.contains("java")) {
            skills.add("Java");
        }
        if (lower.contains("spring")) {
            skills.add("Spring Boot");
        }
        if (lower.contains("docker")) {
            skills.add("Docker");
        }
        if (lower.contains("remote")) {
            skills.add("Remote");
        }
        return skills;
    }
}