package com.cvscanner.controller;
import com.cvscanner.entity.CandidateEntity;
import com.cvscanner.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @GetMapping("/filter")
    public List<CandidateEntity> filterCandidates(
            @RequestParam String skill,
            @RequestParam int experience
    ) {
        return candidateService
                .filterCandidates(skill, experience);
    }
}