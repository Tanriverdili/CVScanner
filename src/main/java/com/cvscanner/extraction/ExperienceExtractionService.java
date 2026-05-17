package com.cvscanner.extraction;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExperienceExtractionService {
    public int extractExperience(String content) {
        Pattern pattern = Pattern.compile("(\\d+)\\s+years");
        Matcher matcher = pattern.matcher(content.toLowerCase());

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }
}