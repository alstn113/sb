package com.sb.ui;

import com.sb.application.solution.SolutionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolutionController {

    private final SolutionService solutionService;

    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }
}
