package com.cvscanner.service;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TextReaderService {
    public String readTextFile(Path filePath) {

        try {
            return Files.readString(filePath);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read file");
        }
    }
}