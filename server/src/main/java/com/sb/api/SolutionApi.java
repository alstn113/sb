package com.sb.api;

import java.net.URI;
import com.sb.api.auth.Auth;
import com.sb.application.auth.Accessor;
import com.sb.application.solution.SolutionResponse;
import com.sb.application.solution.SolutionService;
import com.sb.application.solution.StartSolutionRequest;
import com.sb.application.solution.SubmitSolutionRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolutionApi {

    private final SolutionService solutionService;

    public SolutionApi(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @GetMapping("/solutions/me/in-progress")
    public ResponseEntity<Void> getInProgressSolution(@Auth Accessor accessor) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/solutions/me/submitted")
    public ResponseEntity<Void> getSubmittedSolution(@Auth Accessor accessor) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/solutions/start")
    public ResponseEntity<SolutionResponse> startSolution(
            @Auth Accessor accessor,
            @Valid @RequestBody StartSolutionRequest request
    ) {
        SolutionResponse response = solutionService.startSolution(accessor, request);

        URI location = URI.create("/solutions/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/solutions/submit")
    public ResponseEntity<SolutionResponse> submitSolution(
            @Auth Accessor accessor,
            @Valid @RequestBody SubmitSolutionRequest request
    ) {
        SolutionResponse response = solutionService.submitSolution(accessor, request);

        return ResponseEntity.ok(response);
    }
}
