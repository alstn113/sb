package com.sb.ui;

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
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping("/missions")
    public ResponseEntity<List<MissionResponse>> getMissions() {
        List<MissionResponse> responses = missionService.getMissions();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/missions/{id}")
    public ResponseEntity<MissionResponse> getMission(@PathVariable long id) {
        MissionResponse response = missionService.getMission(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/missions")
    public ResponseEntity<MissionResponse> createMission(@Valid @RequestBody MissionRequest missionRequest) {
        MissionResponse response = missionService.createMission(missionRequest);

        URI location = URI.create("/missions/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/missions/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable long id) {
        missionService.deletePost(id);

        return ResponseEntity.noContent().build();
    }
}
