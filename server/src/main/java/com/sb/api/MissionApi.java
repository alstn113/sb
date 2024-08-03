package com.sb.api;

import java.net.URI;
import java.util.List;
import com.sb.application.mission.MissionRequest;
import com.sb.application.mission.MissionResponse;
import com.sb.application.mission.MissionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MissionApi {

    private final MissionService missionService;

    public MissionApi(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping("/missions")
    public ResponseEntity<List<MissionResponse>> getMissions() {
        List<MissionResponse> responses = missionService.getMissions();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/missions/{missionId}")
    public ResponseEntity<MissionResponse> getMission(@PathVariable Long missionId) {
        MissionResponse response = missionService.getMission(missionId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/missions")
    public ResponseEntity<MissionResponse> createMission(@Valid @RequestBody MissionRequest request) {
        MissionResponse response = missionService.createMission(request);

        URI location = URI.create("/missions/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/missions/{missionId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long missionId) {
        missionService.deletePost(missionId);

        return ResponseEntity.noContent().build();
    }
}
