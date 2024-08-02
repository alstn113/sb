package com.sb.api;

import com.sb.application.solution.SolutionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolutionApi {

    private final SolutionService solutionService;

    public SolutionApi(SolutionService solutionService) {
        this.solutionService = solutionService;
    }
}
