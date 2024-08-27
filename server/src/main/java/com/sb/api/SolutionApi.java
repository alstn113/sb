package com.sb.api;

import java.net.URI;
import java.util.List;
import com.sb.api.auth.Auth;
import com.sb.application.auth.Accessor;
import com.sb.application.solution.SolutionResponse;
import com.sb.application.solution.SolutionService;
import com.sb.application.solution.StartSolutionRequest;
import com.sb.application.solution.SubmitSolutionRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SolutionApi {

    private final SolutionService solutionService;

    @GetMapping("/solutions")
    public ResponseEntity<List<SolutionResponse>> getSolutions() {
        List<SolutionResponse> responses = solutionService.getSolutions();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/solutions/{solutionId}")
    public ResponseEntity<SolutionResponse> getSolutionById(@PathVariable Long solutionId) {
        SolutionResponse response = solutionService.getSolutionById(solutionId);

        return ResponseEntity.ok(response);
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
