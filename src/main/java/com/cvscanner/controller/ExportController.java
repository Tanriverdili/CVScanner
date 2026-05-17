package com.cvscanner.controller;
import com.cvscanner.service.ExcelExportService;
import com.cvscanner.service.ExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.FileInputStream;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
public class ExportController {
    private final ExportService exportService;
    private final ExcelExportService excelExportService;

    @GetMapping("/excel")
    public ResponseEntity<InputStreamResource> exportExcel() {
        try {
            InputStreamResource resource =
                    new InputStreamResource(
                            excelExportService.exportToExcel()
                    );
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=candidates.xlsx"
                    )
                    .contentType(
                            MediaType.parseMediaType(
                                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                            )
                    )
                    .body(resource);

        } catch (Exception e) {
            throw new RuntimeException("Excel download failed");
        }
    }
    @GetMapping("/csv")
    public ResponseEntity<InputStreamResource> exportCsv() {
        try {
            Path path = exportService.exportToCsv();

            InputStreamResource resource =
                    new InputStreamResource(
                            new FileInputStream(path.toFile())
                    );
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=candidates.csv")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (Exception e) {
            throw new RuntimeException("Download failed");
        }
    }
}