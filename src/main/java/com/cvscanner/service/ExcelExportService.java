package com.cvscanner.service;
import com.cvscanner.entity.CandidateEntity;
import com.cvscanner.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelExportService {
    private final CandidateRepository candidateRepository;

    public ByteArrayInputStream exportToExcel() throws IOException {
        List<CandidateEntity> candidates =
                candidateRepository.findAll();

        XSSFWorkbook workbook =
                new XSSFWorkbook();

        XSSFSheet sheet =
                workbook.createSheet("Candidates");

        Row header =
                sheet.createRow(0);

        header.createCell(0).setCellValue("Name");
        header.createCell(1).setCellValue("Skills");
        header.createCell(2).setCellValue("Experience");

        int rowNum = 1;

        for (CandidateEntity candidate : candidates) {

            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(candidate.getName());

            row.createCell(1)
                    .setCellValue(candidate.getSkills());

            row.createCell(2)
                    .setCellValue(candidate.getExperience());
        }

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        workbook.write(out);

        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
