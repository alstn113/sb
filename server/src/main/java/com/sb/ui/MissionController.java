package com.sb.ui;

import java.net.URI;
import java.util.List;
import com.sb.ui.auth.Auth;
import com.sb.application.auth.Accessor;
import com.sb.application.mission.MissionRequest;
import com.sb.application.mission.MissionResponse;
import com.sb.application.mission.MissionService;
import com.sb.application.mission.MissionWithStatusResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/api/v1/missions")
    public ResponseEntity<List<MissionResponse>> getMissions() {
        List<MissionResponse> responses = missionService.getMissions();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/api/v1/missions/{missionId}")
    public ResponseEntity<MissionWithStatusResponse> getMission(
            @Auth(required = false) Accessor accessor,
            @PathVariable Long missionId
    ) {
        MissionWithStatusResponse response = missionService.getMission(accessor, missionId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/v1/missions")
    public ResponseEntity<MissionResponse> createMission(@Valid @RequestBody MissionRequest request) {
        MissionResponse response = missionService.createMission(request);

        URI location = URI.create("/api/v1/missions/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/api/v1/missions/{missionId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long missionId) {
        missionService.deletePost(missionId);

        return ResponseEntity.noContent().build();
    }
}
