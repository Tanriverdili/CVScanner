package com.cvscanner.extraction;
import org.springframework.stereotype.Service;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.FileInputStream;


@Service
public class ZipExtractionService {
    public void extractZip(Path zipFilePath) {
        System.out.println("EXTRACT STARTED");

        try {
            Path tempDir = Paths.get("temp-cvs");
            Files.createDirectories(tempDir);

            try (ZipInputStream zis =
                         new ZipInputStream(new FileInputStream(zipFilePath.toFile()))) {

                ZipEntry entry;

                while ((entry = zis.getNextEntry()) != null) {

                    System.out.println("ENTRY FOUND: " + entry.getName());

                    if (entry.isDirectory()) continue;

                    String fileName = Paths.get(entry.getName())
                            .getFileName()
                            .toString();

                    Path outPath = tempDir.resolve(fileName);

                    Files.copy(zis, outPath, StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("SAVED: " + outPath);

                    zis.closeEntry();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}