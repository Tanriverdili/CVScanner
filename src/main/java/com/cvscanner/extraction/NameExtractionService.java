package com.cvscanner.extraction;
import org.springframework.stereotype.Service;

@Service
public class NameExtractionService {
    public String extractName(String content) {
        String[] lines = content.split("\n");
        return lines[0];
    }
}