package com.cvscanner.controller;
import com.cvscanner.extraction.ZipExtractionService;
import com.cvscanner.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.cvscanner.storage.FileStorageService;
import org.springframework.http.MediaType;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/cv")
@RequiredArgsConstructor
public class CVUploadController {
    private  final Logger logger = LoggerFactory.getLogger(CVUploadController.class);
    private final FileStorageService fileStorageService;
    private final ZipExtractionService zipExtractionService;
    private final CVProcessingService cvProcessingService;

    @Operation(summary = "Upload ZIP file containing CVs")
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String uploadZip(@RequestParam("file") MultipartFile file) {
        logger.info("CV processing started");

        try {
            fileStorageService.saveZipFile(file);

            Path zipPath =
                    Paths.get("uploads")
                            .resolve(file.getOriginalFilename());

            System.out.println("ZIP PATH: " + zipPath.toAbsolutePath());

            zipExtractionService.extractZip(zipPath);
            cvProcessingService.runJob();
            logger.info("CV processing finished successfully");
            return "ZIP uploaded and extracted successfully";

        } catch (Exception e) {
            logger.error("Error while processing CV", e);
            return "Error occurred while uploading CV";
        }
    }
}
























